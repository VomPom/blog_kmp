package com.vompom.blog.ui.component

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

/**
 *
 * Created by @juliswang on 2025/06/23 22:37
 *
 * @Description
 */
val LocalWindowSizeClass: ProvidableCompositionLocal<WindowSizeClass> =
    staticCompositionLocalOf { error("No window size class provided") }