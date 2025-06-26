package com.vompom.blog.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 *
 * Created by @juliswang on 2025/06/12 20:57
 *
 * @Description
 */

@Composable
fun ContentContainer(
    modifier: Modifier = Modifier,
    title: String,
    titleSize: TextUnit = 16.sp,
    titleWeight: FontWeight = FontWeight.Bold,
    visibility: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
) {
    if (visibility) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .background(
                    color = MaterialTheme.colorScheme.onSecondary,
                    shape = RoundedCornerShape(5.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 5.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Column {
                    Text(
                        text = title,
                        style = TextStyle.Default.copy(
                            fontSize = titleSize,
                            fontWeight = titleWeight,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    content()
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}
