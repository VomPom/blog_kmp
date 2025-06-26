package com.vompom.blog.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.vompom.blog.data.model.TabInfo
import com.vompom.blog.navigation.NavigationActions
import com.vompom.blog.ui.component.LocalWindowSizeClass
import com.vompom.blog.ui.post.PostPanelScreen
import com.vompom.blog.utils.ext.isCompact

/**
 *
 * Created by @juliswang on 2025/06/06 21:47
 *
 * @Description
 */
enum class HomeScreenType {
    Post, Stats, Mine
}


private val tabs = listOf(
    TabInfo.POST,
    TabInfo.STATS,
    TabInfo.MINE
)

@Composable
fun HomeScreen(navController: NavHostController, navigationActions: NavigationActions) {
    val craneScreenValues = HomeScreenType.entries.toTypedArray()
    val pagerState = rememberPagerState(initialPage = HomeScreenType.Post.ordinal) { craneScreenValues.size }
    val windowSizeClass: WindowSizeClass = LocalWindowSizeClass.current
    val layoutDirection = LocalLayoutDirection.current
    var pagIndex by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier
    ) { contentPadding ->
        if (windowSizeClass.isCompact()) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.padding(
                    top = contentPadding.calculateTopPadding(),
                    bottom = contentPadding.calculateBottomPadding(),
                ),
            ) { page ->
                CreateScreen(tabs[page].title, craneScreenValues[page], navController, navigationActions)
            }
        } else {
            Row(
                modifier = Modifier.padding(
                    start = contentPadding.calculateStartPadding(layoutDirection = layoutDirection),
                    end = contentPadding.calculateEndPadding(layoutDirection = layoutDirection),
                    bottom = contentPadding.calculateBottomPadding(),
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SideNavigation(current = pagIndex) {
                    pagIndex = it
                }
                Crossfade(
                    targetState = pagIndex,
                    modifier = Modifier.fillMaxSize(),
                    content = {
                        CreateScreen(
                            tabs[pagIndex].title,
                            craneScreenValues[pagIndex],
                            navController,
                            navigationActions
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun CreateScreen(
    title: String,
    type: HomeScreenType,
    navController: NavHostController,
    navigationActions: NavigationActions,
) {
    return when (type) {
        HomeScreenType.Post -> {
            PostPanelScreen(
                onBackClick = { navController.popBackStack() },
                onPostClick = { post -> navigationActions.goToPostDetail(post) },
                onTagClick = { tag -> navigationActions.goToPostType(tag) },
                onCategoryClick = { category -> navigationActions.goToPostType(category) }
            )
        }

        HomeScreenType.Stats -> {
            StatsScreen(
                title,
                onBackClick = { navController.popBackStack() },
                onTagClick = { tag -> navigationActions.goToPostType(tag) },
                onCategoryClick = { category -> navigationActions.goToPostType(category) },
                onStatsClick = { scene -> navigationActions.goToPostType(scene) }
            )
        }

        HomeScreenType.Mine -> {
            MineScreen(
                title,
                onBackClick = { navController.popBackStack() },
                onDebugClicked = {
                    navigationActions.gotoDebug()
                })
        }
    }
}


@Composable
private fun SideNavigation(
    modifier: Modifier = Modifier,
    current: Int,
    onItemClick: (Int) -> Unit,
) {
    NavigationRail(modifier = modifier.zIndex(1f)) {
        HomeScreenType.entries.toTypedArray()
            .mapIndexed { index, lifeState ->
                NavigationRailItem(
                    modifier = Modifier,
                    selected = current == index,
                    onClick = {
                        onItemClick(index)
                    },
                    icon = {
                        Icon(
                            imageVector = tabs[index].icon,
                            contentDescription = tabs[index].title,
                        )
                    },
                    label = {
                        Text(text = tabs[index].title)
                    },
                    colors = NavigationRailItemDefaults.colors(

                    )
                )
            }
    }
}


typealias OnBackClick = () -> Unit