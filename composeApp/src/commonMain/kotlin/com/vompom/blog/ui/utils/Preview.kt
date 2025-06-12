package com.vompom.blog.ui.utils

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.vompom.blog.ui.theme.VMTheme

/**
 * Created by @juliswang on 2025/06/12 20:41
 *
 * @Description
 */

@Composable
fun PreviewWrapper(content: @Composable () -> Unit) {
    VMTheme {
        Surface {
            content()
        }
    }
}