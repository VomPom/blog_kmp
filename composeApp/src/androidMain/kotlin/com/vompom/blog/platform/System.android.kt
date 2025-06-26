package com.vompom.blog.platform

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat

actual fun getCurrentThread(): String {
    return "Thread[${Thread.currentThread().name}] (JVM)"
}

@Composable
actual fun StatusAppearanceColor(isDark: Boolean) {
    val activity = LocalActivity.current as ComponentActivity
    val window = activity.window
    WindowCompat.getInsetsController(window, window.decorView).apply {
        isAppearanceLightStatusBars = !isDark
    }
}

