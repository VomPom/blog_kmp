package com.vompom.blog.ui.post

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vompom.blog.data.model.PageResponse
import com.vompom.blog.ui.component.ContentContainer
import com.vompom.blog.ui.component.ScreenContainer
import com.vompom.blog.viewmodel.PostViewModel
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by @juliswang on 2025/05/22 19:12
 *
 * @Description
 */
@Composable
fun PostScreen() {
    val viewModel = koinViewModel<PostViewModel>()
    val categoryList by viewModel.loadAllPostPagePages().collectAsStateWithLifecycle(PageResponse(""))

    LaunchedEffect(Unit) {
    }

    ScreenContainer("博客文章(刚刚更新)", Icons.Filled.Refresh, action = {

    }) {
        ContentContainer("empty") {
            PostList()
            Text(categoryList?.api ?: "")
        }
    }
}

@Composable
fun PostList() {

}