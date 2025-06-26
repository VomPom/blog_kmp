package com.vompom.blog

import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.vompom.blog.navigation.NavigationActions
import com.vompom.blog.navigation.Routes
import com.vompom.blog.ui.DebugScreen
import com.vompom.blog.ui.HomeScreen
import com.vompom.blog.ui.post.PostDetailScreen
import com.vompom.blog.ui.post.PostTypeScreen
import com.vompom.blog.ui.theme.VMTheme
import com.vompom.blog.viewmodel.MainViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
@Preview
fun App() {
    val mainViewModel = koinViewModel<MainViewModel>()
    val appThemeState by mainViewModel.appTheme.collectAsState()
    val isDarkTheme by remember(appThemeState) { derivedStateOf { appThemeState == 1 } }

    VMTheme(isDarkTheme) {
        Surface {
            val navController: NavHostController = rememberNavController()
            val navigationActions = remember(navController) {
                NavigationActions(navController)
            }
            NavHost(navController = navController, startDestination = Routes.Home()) {
                composable<Routes.Home> {
                    HomeScreen(navController, navigationActions)
                }
                composable<Routes.PostType> { backStackEntry ->
                    val postType = backStackEntry.toRoute<Routes.PostType>()
                    PostTypeScreen(
                        postType = postType,
                        onPostClick = { post -> navigationActions.goToPostDetail(post) },
                        onBackClick = { navController.popBackStack() },
                        onTagClick = { tag -> navigationActions.goToPostType(tag) },
                        onCategoryClick = { category -> navigationActions.goToPostType(category) }
                    )
                }

                composable<Routes.PostDetail> { backStackEntry ->
                    val postDetail = backStackEntry.toRoute<Routes.PostDetail>()
                    PostDetailScreen(
                        postDetail,
                        onBackClick = {
                            navController.popBackStack()
                        })
                }
                composable<Routes.Debug> { backStackEntry ->
                    DebugScreen(
                        navController,
                        onBackClick = {
                            navController.popBackStack()
                        })
                }
            }
        }
    }
}
