package com.vompom.blog

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.vompom.blog.navigation.Routes
import com.vompom.blog.ui.DebugScreen
import com.vompom.blog.ui.HomeScreen
import com.vompom.blog.ui.post.PostDetailScreen
import com.vompom.blog.ui.post.PostTypeScreen
import com.vompom.blog.ui.theme.VMTheme
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    VMTheme {
        Surface {
            val navController: NavHostController = rememberNavController()
            NavHost(navController = navController, startDestination = Routes.Home()) {
                composable<Routes.Home> {
                    HomeScreen(navController)
                }
                composable<Routes.PostType> { backStackEntry ->
                    val postType = backStackEntry.toRoute<Routes.PostType>()
                    PostTypeScreen(postType)
                }
                
                composable<Routes.PostDetail> { backStackEntry ->
                    val postDetail = backStackEntry.toRoute<Routes.PostDetail>()
                    PostDetailScreen(postDetail)
                }
                composable<Routes.Debug> { backStackEntry ->
                    DebugScreen(navController)
                }
            }
        }
    }
}
