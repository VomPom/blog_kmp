package com.vompom.blog.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vompom.blog.ui.post.PostScreen

/**
 *
 * Created by @juliswang on 2025/06/06 21:47
 *
 * @Description
 */
enum class CraneScreen {
    Post, Stats, Mine
}

@Composable
fun HomeScreen() {
    val craneScreenValues = CraneScreen.entries.toTypedArray()
    val pagerState = rememberPagerState(initialPage = CraneScreen.Post.ordinal) { craneScreenValues.size }

    Scaffold(
        modifier = Modifier
    ) { contentPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(
                top = contentPadding.calculateTopPadding(),
                bottom = contentPadding.calculateBottomPadding(),
                start = 10.dp,
                end = 10.dp
            ),
        ) { page ->
            when (craneScreenValues[page]) {
                CraneScreen.Post -> {
                    PostScreen()
                }

                CraneScreen.Stats -> {
                    StatsScreen()
                }

                CraneScreen.Mine -> {
                    MineScreen()
                }
            }
        }
    }
}