package com.vompom.blog.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

/**
 *
 * Created by @juliswang on 2025/06/26 21:42
 *
 * @Description
 */

enum class TabInfo(val title: String, val icon: ImageVector) {
    POST("文章", Icons.AutoMirrored.Filled.Article),
    STATS("统计", Icons.Default.QueryStats),
    MINE("我", Icons.Default.Settings),
}
