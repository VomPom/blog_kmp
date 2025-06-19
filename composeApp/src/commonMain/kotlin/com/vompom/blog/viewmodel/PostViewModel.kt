package com.vompom.blog.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vompom.blog.data.model.*
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

    //todo:: simple...
    var api: String = ""
    private val _uiState = MutableStateFlow(PostUiState())
    val uiState: StateFlow<PostUiState> = _uiState.asStateFlow()
    private val _pages = mutableStateListOf<Page>()
    private var _maxPage = 0


    private val paginator = Paginator(
        scope = viewModelScope,
        initialApi = api,
        initialKey = 1,
        incrementBy = 1,
        onLoadUpdated = { isLoading ->
            println("onLoadUpdated:$isLoading}")
            _uiState.update {
                it.copy(isLoading = isLoading)
            }
        },
        onRequest = { nextKey ->
            loadPosts(api, nextKey)
        },
        onError = { throwable ->
            _uiState.update { it.copy(errorMessage = throwable.message) }
        },
        onSuccess = { items, _ ->
            _uiState.update { currentState ->
                currentState.copy(
                    posts = currentState.posts + items
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

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun loadPosts(api: String, index: Int): Flow<UiState<List<Post>>> {
        return flow {
            if (_pages.isEmpty()) {
                loadPostPageByType(api)
                    .flatMapConcat {
                        realLoadPosts(0)
                    }
                    // todo:: why first is ok?
                    .collectIndexed { index, page ->
                        emit(UiState.Success(page))
                    }
//                    .first()
//                    .collect { posts ->
//                        emit(UiState.Success(posts)) // 最终数据
//                    }
//                emit(UiState.Success(posts))
            } else {
                realLoadPosts(index).collect { posts ->
                    emit(UiState.Success(posts))
                }
            }

        }
    }


    fun loadPosts(api: String) {
        this.api = api
        paginator.loadNextItems()
    }

    fun loadAllPostPagePages(): Flow<PageResponse> = repository.getAllPostPages()

    //    fun loadPostPage(api: String): Flow<PageResponse> = repository.getPostPage(api)
    fun getPosts(api: String): Flow<PostResponse> = repository.getPosts(api)


}