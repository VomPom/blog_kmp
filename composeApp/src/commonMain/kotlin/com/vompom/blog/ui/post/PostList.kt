package com.vompom.blog.ui.post

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vompom.blog.ui.component.VMToolbar
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
    viewModel.getData()
    Column(modifier = Modifier.fillMaxSize()) {
        VMToolbar("博客文章(刚刚更新)", Icons.Filled.Mode)
        PostList()
    }
}

@Composable
fun PostList() {

}