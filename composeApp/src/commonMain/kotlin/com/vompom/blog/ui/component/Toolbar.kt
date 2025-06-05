package com.vompom.blog.ui.component

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Created by @juliswang on 2025/05/28 20:04
 *
 * @Description
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VMToolbar(title: String = "", icon: ImageVector? = null, action: (() -> Unit)? = null) {
    TopAppBar(
        title = {
            Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        },
        actions = {
            if (icon != null) {
                IconButton(onClick = { action?.invoke() }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Localized description"
                    )
                }
            }
        }
    )
}