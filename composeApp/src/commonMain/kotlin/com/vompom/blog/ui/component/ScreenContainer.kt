package com.vompom.blog.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 *
 * Created by @juliswang on 2025/06/17 21:28
 *
 * @Description
 */

@Composable
fun ScreenContainer(
    title: String = "",
    icon: ImageVector? = null,
    withBackIcon: Boolean = false,
    action: (() -> Unit)? = null,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(
            top = paddingValues.calculateTopPadding(),
            bottom = paddingValues.calculateBottomPadding(),
            start = 10.dp,
            end = 10.dp
        ),
    ) {
        VMToolbar(title, icon, withBackIcon, action)
        content()
    }
}