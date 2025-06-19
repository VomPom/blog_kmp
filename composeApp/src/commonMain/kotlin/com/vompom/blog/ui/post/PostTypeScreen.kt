package com.vompom.blog.ui.post

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.vompom.blog.navigation.Routes
import com.vompom.blog.ui.component.ContentColumn
import com.vompom.blog.ui.component.OnPagination
import com.vompom.blog.ui.component.ScreenContainer
import com.vompom.blog.viewmodel.PostViewModel
import org.koin.compose.viewmodel.koinViewModel

/**
 *
 * Created by @juliswang on 2025/06/06 18:45
 *
 * @Description
 */
@Composable
fun PostTypeScreen(postType: Routes.PostType, onPostClick: () -> Unit = {}) {
    val viewModel = koinViewModel<PostViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()
    LaunchedEffect(Unit) {
        viewModel.loadPosts(postType.api)
    }
    Scaffold(
        modifier = Modifier
    ) { paddingValues ->
        ScreenContainer(postType.title, withBackIcon = true, paddingValues = paddingValues) {
            ContentColumn(
                modifier = Modifier.fillMaxSize(),
                loading = uiState.isLoading,
                errorMessage = uiState.errorMessage,
            ) {
                LazyColumn(state = listState) {
                    items(uiState.posts.size) { index ->
                        PostSummary(uiState.posts[index], index, scene = PostItemScene.DEFAULT, onPostClick)
                    }
                }
                OnPagination(listState) {
                    viewModel.loadPosts(postType.api)
                }
            }
        }
    }
}