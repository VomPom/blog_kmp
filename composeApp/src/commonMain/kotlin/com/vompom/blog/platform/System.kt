package com.vompom.blog.platform

import androidx.compose.runtime.Composable

/**
 *
 * Created by @juliswang on 2025/06/19 21:13
 *
 * @Description
 */


expect fun getCurrentThread(): String

/**
 * StatusBar text's color
 *
 * @param isDark
 */
@Composable
expect fun StatusAppearanceColor(isDark: Boolean)
