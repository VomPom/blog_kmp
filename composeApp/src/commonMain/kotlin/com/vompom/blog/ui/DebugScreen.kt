package com.vompom.blog.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.vompom.blog.ui.component.PageState
import com.vompom.blog.ui.component.ScreenContainer
import com.vompom.blog.viewmodel.PostViewModel
import org.koin.compose.viewmodel.koinViewModel

/**
 *
 * Created by @juliswang on 2025/06/18 21:01
 *
 * @Description
 */

@Composable
fun DebugScreen(navController: NavHostController) {
    val postViewModel = koinViewModel<PostViewModel>()
    Scaffold(
        modifier = Modifier
    ) { paddingValues ->
        ScreenContainer("Debug", withBackIcon = true, paddingValues = paddingValues) {
            Column {
                RequestItem("") {
                    val posts by postViewModel.getPosts("api/tags/travel/page.1.json")
                        .collectAsStateWithLifecycle(PageState.Loading)
                    Text(posts.toString())
                }
            }
        }
    }

}

@Composable
fun RequestItem(title: String, action: @Composable () -> Unit = {}) {
    Text(text = title)
    action()
}