package com.vompom.blog.ui.post

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.vompom.blog.ui.OnBackClick
import com.vompom.blog.ui.component.ScreenContainer

/**
 *
 * Created by @juliswang on 2025/06/19 21:00
 *
 * @Description
 */
@Composable
fun PostDetailScreen(
    data: Routes.PostDetail,
    onBackClick: OnBackClick,
) {
    val state = rememberWebViewState("${AppConfig.BASE_URL}/${data.url}")

    Scaffold(
        modifier = Modifier
    ) { paddingValues ->
        ScreenContainer(data.title, onBackClick, withBackIcon = true, paddingValues = paddingValues) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (data.content.isNotEmpty()) {
                    Column {
                        // todo:: render content.
                        Text(data.content)
                    }
                } else {
                    if (state.isLoading) {
                        Text(text = "Loading...", style = TextStyle(fontSize = 20.sp))
                    }
                    WebView(
                        state = state,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}