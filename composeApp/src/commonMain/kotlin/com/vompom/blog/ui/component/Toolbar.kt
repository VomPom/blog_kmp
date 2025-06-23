package com.vompom.blog.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vompom.blog.ui.OnBackClick

/**
 * Created by @juliswang on 2025/05/28 20:04
 *
 * @Description
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VMToolbar(
    title: String = "",
    icon: ImageVector? = null,
    withBackIcon: Boolean = false,
    onBackClick: OnBackClick,
    action: (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (withBackIcon) {
                IconButton(
                    modifier = Modifier.size(36.dp),
                    onClick = { onBackClick() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Localized description"
                    )
                }
            }
            Text(
                modifier = Modifier,
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        if (icon != null) {
            IconButton(
                modifier = Modifier.size(36.dp),
                onClick = { action?.invoke() }) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Localized description"
                )
            }
        }
    }
}