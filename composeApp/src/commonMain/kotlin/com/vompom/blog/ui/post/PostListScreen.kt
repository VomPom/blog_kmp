package com.vompom.blog.ui.post

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.vompom.blog.ui.OnCategoryClicked
import com.vompom.blog.ui.OnPostClick
import com.vompom.blog.ui.OnTagClicked
import com.vompom.blog.ui.component.ContentColumn
import com.vompom.blog.ui.component.OnPagination
import com.vompom.blog.ui.component.ScreenContainer
import com.vompom.blog.viewmodel.PostViewModel
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by @juliswang on 2025/05/22 19:12
 *
 * @Description
 */
@Composable
fun PostScreen(
    onPostClick: OnPostClick,
    onTagClicked: OnTagClicked,
    onCategoryClicked: OnCategoryClicked,
) {
    val viewModel = koinViewModel<PostViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        viewModel.loadPosts("/api/posts.json")
    }

    ScreenContainer(
        "博客文章(刚刚更新)",
        withBackIcon = false,
        icon = Icons.Rounded.Refresh,
        action = {
            viewModel.fresh()
        }) {
        ContentColumn(
            modifier = Modifier.fillMaxSize(),
            loading = uiState.isLoading,
            errorMessage = uiState.errorMessage,
        ) {
            LazyColumn(state = listState) {
                items(uiState.posts.size) { index ->
                    PostSummary(
                        uiState.posts[index],
                        index,
                        scene = PostItemScene.DEFAULT,
                        onPostClick,
                        onTagClicked,
                        onCategoryClicked
                    )
                }
            }
            OnPagination(listState) {
                viewModel.loadMore()
            }
        }
    }
}