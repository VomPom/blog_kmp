package com.vompom.blog.utils.ext

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Compact

/**
 *
 * Created by @juliswang on 2025/06/24 21:28
 *
 * @Description
 */

fun WindowSizeClass.isCompact() = this.widthSizeClass == Compact