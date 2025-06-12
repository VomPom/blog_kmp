package com.vompom.blog.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import blog_kmp.composeapp.generated.resources.Res
import blog_kmp.composeapp.generated.resources.ic_folder
import com.vompom.blog.data.model.Category
import org.jetbrains.compose.resources.painterResource

/**

 * Created by @juliswang on 2025/05/30 10:47
 *
 * @Description
 */

@Composable
fun CategoryItem(
    data: Category,
    contentSize: Float = 12f,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .padding(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_folder),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 5.dp, end = 2.dp)
                .size((contentSize * 1.2).dp),
            tint = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = data.name,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = contentSize.sp
        )

        Text(
            text = "(${data.count})",
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            fontSize = 12.sp
        )
    }
}