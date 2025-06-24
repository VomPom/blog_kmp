package com.vompom.blog.navigation

import androidx.navigation.NavHostController
import com.vompom.blog.data.model.Category
import com.vompom.blog.data.model.Post
import com.vompom.blog.data.model.Tag
import com.vompom.blog.ui.StatsScene

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
            is Int -> {
                val title = when (it) {
                    StatsScene.CHARACTER -> "字数统计"
                    StatsScene.ALL_POST -> "全部文章"
                    else -> {
                        ""
                    }
                }
                navController.navigate(Routes.PostType(title = title, scene = it))
            }
        }
    }


    val gotoDebug: () -> Unit = {
        navController.navigate(Routes.Debug())
    }

    val goToPostDetail: (Post) -> Unit = { it ->
        navController.navigate(Routes.PostDetail(title = it.title, it.url))
    }
}