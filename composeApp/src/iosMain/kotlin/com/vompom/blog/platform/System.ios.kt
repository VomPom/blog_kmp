package com.vompom.blog.platform

import androidx.compose.runtime.Composable
import platform.Foundation.NSThread

actual fun getCurrentThread(): String {
    return "Thread[${NSThread.currentThread().name}] (NSThread)"
}

@Composable
actual fun StatusAppearanceColor(isDark: Boolean) {
    //no-op
}

actual fun openWebBrowser(url: String) {
    // no-op
}