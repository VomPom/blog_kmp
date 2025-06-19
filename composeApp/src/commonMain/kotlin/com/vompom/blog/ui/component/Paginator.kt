package com.vompom.blog.ui.component

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

/**
 *
 * Created by @juliswang on 2025/06/19 21:14
 *
 * @Description copy from https://github.com/devexpert-io/kmp-movies
 */
class Paginator<T>(
    private val scope: CoroutineScope,
    private var initialApi: String? = null,
    private val initialKey: Int = 1,
    private val incrementBy: Int = 1,
    private val onLoadUpdated: (Boolean) -> Unit,
    private val onRequest: suspend (nextKey: Int) -> Flow<UiState<List<T>>>,
    private val onError: suspend (Throwable) -> Unit,
    private val onSuccess: suspend (items: List<T>, newKey: Int) -> Unit,
) {
    private var isMakingRequest = false
    private var currentKey = initialKey
    private var hasError = false  // NEW FLAG

    fun reset() {
        currentKey = initialKey
        hasError = false
    }

    fun loadNextItems() {
        if (isMakingRequest || hasError) return  // Prevent further loading if error occurred

        isMakingRequest = true
        scope.launch {
            onLoadUpdated(true)
            val resultFlow = onRequest(currentKey)
            resultFlow
                .onCompletion {
                    //no-op
                }
                .catch { throwable ->
                    //no-op
                }
                .collect { result ->
                    when (result) {
                        is UiState.Loading -> Unit
                        is UiState.Success -> {
                            val items = result.data.orEmpty()
                            println("UiState.Success:items = ${items.size}")
                            val nextKey = currentKey + incrementBy
                            onSuccess(items, nextKey)
                            currentKey = nextKey
                        }

                        is UiState.Error -> {
                            hasError = true  // STOP FURTHER PAGINATION
                            onError(result.exception)
                        }
                    }
                }
            onLoadUpdated(false)
            isMakingRequest = false
        }
    }
}

@Composable
fun OnPagination(
    listState: LazyListState,
    buffer: Int = 5,
    onLoadMore: () -> Unit,
) {
    val shouldLoadMore = remember {
        derivedStateOf {
            if (listState.layoutInfo.visibleItemsInfo.isNotEmpty()) {
                println("listState.layoutInfo.totalItemsCount:${listState.layoutInfo.totalItemsCount}")
                println(" listState.layoutInfo.visibleItemsInfo.last().index:${listState.layoutInfo.visibleItemsInfo.last().index}")
            }

            listState.layoutInfo.visibleItemsInfo.isNotEmpty() &&
                    listState.layoutInfo.visibleItemsInfo.last().index >= listState.layoutInfo.totalItemsCount - 3
        }
    }

    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value) {
            onLoadMore()
        }
        println("shouldLoadMore.value:${shouldLoadMore.value}")
    }
}

sealed class UiState<out R> {
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val exception: Exception) : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
}