package com.vompom.blog.ui.post

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
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

    val state = rememberWebViewState("${AppConfig.BASE_URL}/${data.url}")
    Scaffold(
        modifier = Modifier
    ) { paddingValues ->
        ScreenContainer(data.title, withBackIcon = true, paddingValues = paddingValues) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                WebView(
                    state = state,
                    modifier = Modifier.fillMaxSize()
                )
                if (state.isLoading) {
                    Text(text = "Loading...", style = TextStyle(fontSize = 20.sp))
                }
            }
        }
    }
}