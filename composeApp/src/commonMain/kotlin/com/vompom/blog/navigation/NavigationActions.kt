package com.vompom.blog.navigation

import androidx.navigation.NavHostController
import com.vompom.blog.data.model.Category
import com.vompom.blog.data.model.Tag

/**
 *
 * Created by @juliswang on 2025/06/17 21:44
 *
 * @Description
 */

class NavigationActions(navController: NavHostController) {
    val goToPostType: (Any) -> Unit = { it ->
        when (it) {
            is Tag -> navController.navigate(Routes.PostType(it.api, it.name))
            is Category -> navController.navigate(Routes.PostType(it.api, it.name))
        }
    }
    val gotoDebug: () -> Unit = {
        navController.navigate(Routes.Debug())
    }

    val goToPostDetail: (Routes.PostDetail) -> Unit = { it -> navController.navigate(it) }
}