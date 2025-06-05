package com.vompom.blog.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import blog_kmp.composeapp.generated.resources.Res
import blog_kmp.composeapp.generated.resources.ic_post_tag
import com.vompom.blog.data.model.Tag
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Created by @juliswang on 2025/05/28 20:42
 *
 * @Description
 */
@Composable
fun TagItem(
    data: Tag,
    showCount: Boolean = false,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(Color(0xFFF5F5F5))
            .clickable(onClick = onClick)
            .padding(end = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(Res.drawable.ic_post_tag),
            contentDescription = "Tag icon",
            modifier = Modifier.size(15.dp).background(Color.Green)
        )

        Text(
            text = data.name,
            color = Color(0xFF333333),
            fontSize = 12.sp,
            modifier = Modifier.background(Color.Red).wrapContentHeight()
        )

        if (showCount) {
            Text(
                text = "(${data.count})",
                color = Color(0xFF333333),
                fontSize = 8.sp,
                modifier = Modifier.background(Color.Blue).wrapContentHeight()
            )
        }
    }
}

@Preview
@Composable
fun TagItemPreview() {
    TagItem(
        data = Tag(name = "Android", count = 42, api = "api", slug = "android"),
        showCount = true
    )
}