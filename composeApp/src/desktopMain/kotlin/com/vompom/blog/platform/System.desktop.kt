package com.vompom.blog.platform

import androidx.compose.runtime.Composable

actual fun getCurrentThread(): String {
    return Thread.currentThread().name
}

@Composable
actual fun StatusAppearanceColor(isDark: Boolean) {
    //no-op
}