package com.vompom.blog.platform

import androidx.compose.runtime.Composable
import platform.Foundation.NSThread

actual fun getCurrentThread(): String {
  return "Thread[${NSThread.currentThread().name}] (NSThread)"
}

@Composable
actual fun StatusAppearanceColor(isDark: Boolean) {
  // 使用 SideEffect 确保只在需要时更新
  SideEffect {
    updateStatusBarStyle(isDark)
  }
}