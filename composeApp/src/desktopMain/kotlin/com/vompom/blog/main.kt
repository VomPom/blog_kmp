package com.vompom.blog

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.vompom.blog.data.di.initKoin
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
fun main() {
    initKoin()
    application {

        Window(
            onCloseRequest = ::exitApplication,
            title = "blog_kmp",
        ) {
            App()
        }
    }
}

@Preview
@Composable
fun preview() {
    App()
}