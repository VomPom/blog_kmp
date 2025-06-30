package com.vompom.blog

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.vompom.blog.data.di.initKoin
import com.vompom.blog.ui.component.LocalWindowSizeClass

fun main() {
    initKoin {}
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "blog_kmp",
        ) {
            @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
            val windowSizeClass: WindowSizeClass = calculateWindowSizeClass()
            CompositionLocalProvider(
                LocalWindowSizeClass provides windowSizeClass,
            ) {
                App()
            }
        }
    }
}
