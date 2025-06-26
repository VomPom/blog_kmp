package com.vompom.blog.ui.post

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.vompom.blog.data.model.Post
import com.vompom.blog.navigation.Routes
import com.vompom.blog.ui.OnBackClick
import com.vompom.blog.ui.OnCategoryClick
import com.vompom.blog.ui.OnPostClick
import com.vompom.blog.ui.OnTagClick
import com.vompom.blog.ui.component.LocalWindowSizeClass
import com.vompom.blog.ui.component.TwoPanelScaffold
import com.vompom.blog.utils.ext.isCompact

/**
 *
 * Created by @juliswang on 2025/06/24 11:11
 *
 * @Description
 */

@Composable
fun PostPanelScreen(
    onBackClick: OnBackClick,
    onPostClick: OnPostClick,
    onTagClick: OnTagClick,
    onCategoryClick: OnCategoryClick,

    ) {
    var details: Post? by remember { mutableStateOf(null) }
    val windowSizeClass: WindowSizeClass = LocalWindowSizeClass.current
    var showPanel: Boolean by rememberSaveable { mutableStateOf(details != null) }
    var split: Float by remember { mutableStateOf(0.4f) }

    // Reset selection if the window size class changes to compact
    LaunchedEffect(windowSizeClass) {
        showPanel = details != null
    }

    TwoPanelScaffold(
        panelVisibility = showPanel,
        split = split,
        body = {
            PostListScreen(
                onBackClick,
                onPostClick = {
                    if (windowSizeClass.isCompact()) {
                        onPostClick(it)
                    } else {
                        details = it
                        showPanel = true
                    }
                },
                onTagClick,
                onCategoryClick
            )
        },
        panel = {
            if (details != null) {
                PostDetailScreen(
                    Routes.PostDetail(
                        details?.title ?: "",
                        details?.url ?: "",
                        details?.content ?: ""),
                    onBackClick = {
                        showPanel = false
                    },
                )
            }
        }
    )
}