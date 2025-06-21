package com.vompom.blog.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vompom.blog.data.model.ListDataState
import com.vompom.blog.data.model.Page
import com.vompom.blog.data.model.Post
import com.vompom.blog.data.repository.PostRepository
import com.vompom.blog.ui.component.Paginator
import com.vompom.blog.ui.component.UiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

/**
 * Created by @juliswang on 2025/05/27 20:18
 *
 * @Description
 */

class PostViewModel(private val repository: PostRepository) : ViewModel() {
    var pageApi: String = ""
    private val _uiState = MutableStateFlow(ListDataState<Post>())
    val uiState: StateFlow<ListDataState<Post>> = _uiState.asStateFlow()
    private val _pages = mutableStateListOf<Page>()
    private var _maxPage = 0

    private val paginator = Paginator(
        scope = viewModelScope,
        initialKey = 1,
        incrementBy = 1,
        onLoadUpdated = { isLoading ->
            _uiState.update {
                it.copy(isLoading = isLoading)
            }
        },
        onRequest = { nextKey ->
            loadPosts(pageApi, nextKey)
        },
        onError = { throwable ->
            _uiState.update { it.copy(errorMessage = throwable.message) }
        },
        onSuccess = { items, _ ->
            _uiState.update { currentState ->
                currentState.copy(
                    data = currentState.data + items
                )
            }
        }
    )

    private fun loadPostPageByType(api: String): Flow<Unit> = flow {
        repository.getPostPage(api).collect {
            if (it.data == null) {
                _uiState.update { uiState -> uiState.copy(errorMessage = "failed to load post.") }
            } else {
                _pages.addAll(it.data.pages)
                _maxPage = it.data.total
            }
            emit(Unit)
        }
    }

    private fun realLoadPosts(index: Int): Flow<List<Post>> {
        return flow {
            val api = _pages[index].api
            repository.getPosts(api).collect {
                emit(it.data.posts!!)
            }
        }
    }

    /**
     * 加载文章，先请求 pages 获取到之后再请求 post 数据
     *
     * @param initApi
     * @param index
     * @return
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    private fun loadPosts(initApi: String, index: Int): Flow<UiState<List<Post>>> {
        return flow {
            if (_pages.isEmpty()) {
                loadPostPageByType(initApi)
                    .flatMapConcat {    // equals  collect { value -> emitAll(value) }
                        realLoadPosts(0)
                    }
                    .collect { posts ->
                        emit(UiState.Success(posts)) // 最终数据
                    }
            } else {
                realLoadPosts(index).collect { posts ->
                    emit(UiState.Success(posts))
                }
            }
        }
    }

    fun loadPosts(pagApi: String) {
        this.pageApi = pagApi
        paginator.loadNextItems()
    }

    fun fresh() {
        _pages.clear()
        paginator.reset()
        loadPosts(this.pageApi)
    }

    fun loadMore() = loadPosts(this.pageApi)
}