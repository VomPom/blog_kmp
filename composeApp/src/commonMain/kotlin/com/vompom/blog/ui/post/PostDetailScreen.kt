package com.vompom.blog.ui.post

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState
import com.vompom.AppConfig
import com.vompom.blog.navigation.Routes
import com.vompom.blog.ui.component.ScreenContainer
import com.vompom.blog.viewmodel.PostViewModel
import org.koin.compose.viewmodel.koinViewModel

/**
 *
 * Created by @juliswang on 2025/06/19 21:00
 *
 * @Description
 */
@Composable
fun PostDetailScreen(data: Routes.PostDetail) {
    val viewModel = koinViewModel<PostViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    val state = rememberWebViewState("${AppConfig.BASE_URL}/${data.url}")
    Scaffold(
        modifier = Modifier
    ) { paddingValues ->
        ScreenContainer(data.title, withBackIcon = true, paddingValues = paddingValues) {
//            ContentColumn(
//                modifier = Modifier.fillMaxSize(),
//                loading = uiState.isLoading,
//                errorMessage = uiState.errorMessage,
//            ) {
//                WebView(state)
//            }
            WebView(state)
        }
    }
}