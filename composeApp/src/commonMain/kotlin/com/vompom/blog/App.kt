package com.vompom.blog

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vompom.blog.ui.HomeScreen
import com.vompom.blog.ui.theme.VMTheme
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    VMTheme {
        Surface {
            val navController: NavHostController = rememberNavController()
            NavHost(navController = navController, startDestination = Home) {
                composable<Home> {
                    HomeScreen()
                }
                composable<PostDetail> {
                    HomeScreen()
                }
            }
        }
    }
}

@Serializable
object Home

@Serializable
object PostDetail

@Serializable
object Tag
