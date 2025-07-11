package com.vompom.blog.ui.post

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vompom.blog.ui.*
import com.vompom.blog.ui.component.ContentColumn
import com.vompom.blog.ui.component.LocalWindowSizeClass
import com.vompom.blog.ui.component.OnPagination
import com.vompom.blog.ui.component.ScreenContainer
import com.vompom.blog.utils.ext.isCompact
import com.vompom.blog.viewmodel.PostViewModel
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by @juliswang on 2025/05/22 19:12
 *
 * @Description
 */
@Composable
fun PostListScreen(
    onBackClick: OnBackClick,
    onPostClick: OnPostClick,
    onTagClick: OnTagClick,
    onCategoryClick: OnCategoryClick,
) {
    val viewModel = koinViewModel<PostViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    val windowSizeClass: WindowSizeClass = LocalWindowSizeClass.current
    val listState: ScrollableState =
        if (windowSizeClass.isCompact()) rememberLazyListState() else rememberLazyStaggeredGridState()
    LaunchedEffect(Unit) {
        viewModel.loadPosts("/api/posts.json")
    }

    ScreenContainer(
        "博客文章(刚刚更新)",
        onBackClick,
        withBackIcon = false,
        icon = Icons.Rounded.Refresh,
        rightAction = {
            viewModel.refresh()
        }) {
        ContentColumn(
            modifier = Modifier.fillMaxSize(),
            loading = uiState.isLoading,
            errorMessage = uiState.errorMessage,
        ) {
            if (windowSizeClass.isCompact()) {
                LazyColumn(state = listState as LazyListState) {
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
                }
            } else {
                LazyVerticalStaggeredGrid(
                    state = listState as LazyStaggeredGridState,
                    columns = StaggeredGridCells.Adaptive(248.dp),
                    modifier = Modifier.padding(5.dp),
                ) {
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
                }
            }
            OnPagination(listState) {
                viewModel.loadMore()
            }
        }
    }
}