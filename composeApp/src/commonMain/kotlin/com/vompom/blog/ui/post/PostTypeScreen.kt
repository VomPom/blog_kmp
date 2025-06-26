package com.vompom.blog.ui.post

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vompom.blog.data.model.Post
import com.vompom.blog.data.model.PostV2
import com.vompom.blog.navigation.Routes
import com.vompom.blog.ui.*
import com.vompom.blog.ui.component.ContentColumn
import com.vompom.blog.ui.component.LocalWindowSizeClass
import com.vompom.blog.ui.component.OnPagination
import com.vompom.blog.ui.component.ScreenContainer
import com.vompom.blog.utils.countChineseChars
import com.vompom.blog.utils.ext.isCompact
import com.vompom.blog.viewmodel.PostViewModel
import org.koin.compose.viewmodel.koinViewModel

/**
 *
 * Created by @juliswang on 2025/06/06 18:45
 *
 * @Description the item shows different UI by postType's scene.
 */
@Composable
fun PostTypeScreen(
    postType: Routes.PostType,
    onBackClick: OnBackClick,
    onPostClick: OnPostClick,
    onTagClick: OnTagClick,
    onCategoryClick: OnCategoryClick,
) {
    val viewModel = koinViewModel<PostViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    val postsState by viewModel.postsState.collectAsState()
    val windowSizeClass: WindowSizeClass = LocalWindowSizeClass.current
    val listState: ScrollableState =
        if (windowSizeClass.isCompact()) rememberLazyListState() else rememberLazyStaggeredGridState()
    val sortedData = remember(postsState.data) {
        postsState.data.sortedByDescending { countChineseChars(it.content) }
    }

    LaunchedEffect(Unit) {
        if (postType.scene == StatsScene.DEFAULT) {
            viewModel.loadPosts(postType.api)
        } else {
            viewModel.loadAllPostSummary()
        }
    }
    Scaffold(
        modifier = Modifier
    ) { paddingValues ->
        ScreenContainer(
            postType.title,
            onBackClick,
            withBackIcon = true,
            paddingValues = paddingValues
        ) {
            ContentColumn(
                modifier = Modifier.fillMaxSize(),
                loading = uiState.isLoading,
                errorMessage = uiState.errorMessage,
                onErrorOKClick = { onBackClick() }
            ) {
                if (windowSizeClass.isCompact()) {
                    LazyColumn(state = listState as LazyListState) {
                        if (postType.isDefault()) {
                            items(uiState.data.size) { index ->
                                PostSummary(
                                    data = uiState.data[index],
                                    index = index + 1,
                                    scene = StatsScene.DEFAULT,
                                    onPostClick = onPostClick,
                                    onTagClick = onTagClick,
                                    onCategoryClick = onCategoryClick
                                )
                            }
                        } else {
                            sortedList(sortedData, postType.scene, onPostClick)
                        }
                    }
                } else {
                    LazyVerticalStaggeredGrid(
                        state = listState as LazyStaggeredGridState,
                        columns = StaggeredGridCells.Adaptive(248.dp),
                        modifier = Modifier.padding(5.dp),
                    ) {
                        if (postType.isDefault()) {
                            items(uiState.data.size) { index ->
                                PostSummary(
                                    uiState.data[index],
                                    index + 1,
                                    scene = StatsScene.DEFAULT,
                                    onPostClick,
                                    onTagClick,
                                    onCategoryClick
                                )
                            }
                        } else {
                            sortedList(sortedData, postType.scene, onPostClick)
                        }
                    }
                }
            }
        }
        if (postType.scene == StatsScene.DEFAULT) {
            OnPagination(listState) {
                viewModel.loadMore()
            }
        }
    }
}


fun LazyStaggeredGridScope.sortedList(
    sortData: List<PostV2>,
    scene: Int,
    onPostClick: OnPostClick,
) {
    items(sortData.size) { index ->
        val data = sortData[index]
        PostSimple(
            data = data,
            index = index + 1,
            scene = scene,
            onPostClick = { onPostClick(Post(url = data.url, title = data.title)) },
        )
    }
}

fun LazyListScope.sortedList(
    sortData: List<PostV2>,
    scene: Int,
    onPostClick: OnPostClick,
) {
    items(sortData.size) { index ->
        val data = sortData[index]
        PostSimple(
            data = data,
            index = index + 1,
            scene = scene,
            onPostClick = { onPostClick(Post(url = data.url, title = data.title)) },
        )
    }
}
