package com.vompom.blog.platform

import androidx.compose.runtime.Composable
import com.vompom.blog.SystemUtils

actual fun getCurrentThread(): String {
    return Thread.currentThread().name
}

@Composable
actual fun StatusAppearanceColor(isDark: Boolean) {
    //no-op
}

actual fun openWebBrowser(url: String) {
    val runtime = Runtime.getRuntime()
    if (SystemUtils.isMac) {
        runtime.exec("open $url")
    } else if (SystemUtils.isWindows) {
        runtime.exec("xdg-open $url")
    } else {
        // no-op
    }
}