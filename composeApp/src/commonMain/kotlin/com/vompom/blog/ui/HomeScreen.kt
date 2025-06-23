package com.vompom.blog.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.vompom.blog.navigation.NavigationActions
import com.vompom.blog.ui.post.PostScreen

/**
 *
 * Created by @juliswang on 2025/06/06 21:47
 *
 * @Description
 */
enum class HomeScreenType {
    Post, Stats, Mine
}

@Composable
fun HomeScreen(navController: NavHostController, navigationActions: NavigationActions) {
    val craneScreenValues = HomeScreenType.entries.toTypedArray()
    val pagerState = rememberPagerState(initialPage = HomeScreenType.Post.ordinal) { craneScreenValues.size }
    Scaffold(
        modifier = Modifier
    ) { contentPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(
                top = contentPadding.calculateTopPadding(),
                bottom = contentPadding.calculateBottomPadding(),
            ),
        ) { page ->
            when (craneScreenValues[page]) {
                HomeScreenType.Post -> {
                    PostScreen(
                        onBackClick = { navController.popBackStack() },
                        onPostClick = { post -> navigationActions.goToPostDetail(post) },
                        onTagClick = { tag -> navigationActions.goToPostType(tag) },
                        onCategoryClick = { category -> navigationActions.goToPostType(category) }
                    )
                }

                HomeScreenType.Stats -> {
                    StatsScreen(
                        onBackClick = { navController.popBackStack() },
                        onTagClick = { tag -> navigationActions.goToPostType(tag) },
                        onCategoryClick = { category -> navigationActions.goToPostType(category) },
                        onStatsClick = { scene -> navigationActions.goToPostType(scene) }
                    )
                }

                HomeScreenType.Mine -> {
                    MineScreen(
                        onBackClick = { navController.popBackStack() },
                        onDebugClicked = {
                            navigationActions.gotoDebug()
                        })
                }
            }
        }
    }
}
typealias OnBackClick = () -> Unit