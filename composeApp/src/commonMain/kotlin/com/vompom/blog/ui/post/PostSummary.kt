package com.vompom.blog.ui.post

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vompom.blog.data.model.Category
import com.vompom.blog.data.model.Post
import com.vompom.blog.data.model.Tag

/**
 *
 * Created by @juliswang on 2025/06/19 21:57
 *
 * @Description
 */
enum class PostItemScene {
    DEFAULT,
    CHARACTER,
}

@Composable
fun PostSummary(
    data: Post,
    index: Int,
    scene: PostItemScene = PostItemScene.DEFAULT,
    onPostClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(5.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(5.dp))
            .background(Color(0xFFF5F5F5)) // 对应 app.color.content_bg
            .padding(5.dp)
            .clickable(onClick = { onPostClick() })
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Title(scene, data.title, index)
            Spacer(Modifier.height(3.dp))

            if (scene == PostItemScene.DEFAULT) {
                TimeAndCategories(index, data.date, data.categories)
                Spacer(Modifier.height(5.dp))
                Summary(data.content)
                Spacer(Modifier.height(5.dp))
                Tags(data.tags)
            }
        }
    }
}

@Composable
private fun Title(scene: PostItemScene, title: String, index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (scene == PostItemScene.CHARACTER) {
            Text(
                text = "[${title.length}字]",
                fontSize = 10.sp,
                modifier = Modifier.padding(end = 4.dp)
            )
        }
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF333333), // 对应 app.color.text_content
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun TimeAndCategories(index: Int, date: String, categories: List<Category>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // 索引标签
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(22.dp, 12.dp)
                .background(Color(0xFFE0E0E0), RoundedCornerShape(5.dp)) // 对应 app.color.tag_bg
        ) {
            Text(
                text = "[$index]",
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // 日期
        Text(
            text = "formatDate(date)",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF888888) // 对应 app.color.post_time
        )

        // 分类
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            categories.forEach { category ->
                CategoryItem(category)
            }
        }
    }
}

@Composable
private fun CategoryItem(category: Category) {
    Text(
        text = category.name,
        fontSize = 12.sp,
        modifier = Modifier
            .padding(horizontal = 6.dp, vertical = 2.dp)
            .background(Color.LightGray, RoundedCornerShape(4.dp))
            .padding(horizontal = 4.dp)
    )
}

@Composable
private fun Summary(content: String) {
    val plainText = content.replace(Regex("<[^>]*>"), "").take(500)
    Text(
        text = plainText,
        fontSize = 13.sp,
        maxLines = 5,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEEEEEE), RoundedCornerShape(5.dp)) // 对应 app.color.summary_bg
            .padding(8.dp)
    )
}

@Composable
private fun Tags(tags: List<Tag>) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(bottom = 5.dp)
    ) {
        tags.forEach { tag ->
            TagItem(tag)
        }
    }
}

@Composable
private fun TagItem(tag: Tag) {
    Text(
        text = tag.name,
        fontSize = 12.sp,
        modifier = Modifier
            .padding(horizontal = 6.dp, vertical = 2.dp)
            .background(Color(0xFFE0E0E0), RoundedCornerShape(4.dp))
            .padding(horizontal = 4.dp)
    )
}

//private fun formatDate(isoDate: String): String {
//    return try {
//        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
//        val date = inputFormat.parse(isoDate)
//        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//        outputFormat.format(date ?: Date())
//    } catch (e: Exception) {
//        isoDate
//    }
//}
