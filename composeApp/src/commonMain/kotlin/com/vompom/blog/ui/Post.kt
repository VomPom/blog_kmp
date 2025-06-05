package com.vompom.blog.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

    Text("Blog Screen")
}