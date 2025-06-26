package com.vompom.blog.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.vompom.blog.ui.OnBackClick

/**
 *
 * Created by @juliswang on 2025/06/17 21:28
 *
 * @Description
 */

@Composable
fun ScreenContainer(
    title: String = "",
    onBackClick: OnBackClick,
    icon: ImageVector? = null,
    withBackIcon: Boolean = false,
    rightAction: (() -> Unit)? = null,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(
            top = paddingValues.calculateTopPadding(),
            bottom = paddingValues.calculateBottomPadding(),
            start = 4.dp,
            end = 4.dp
        ),
    ) {
        VMToolbar(title, icon, withBackIcon, onBackClick, rightAction)
        content()
    }
}