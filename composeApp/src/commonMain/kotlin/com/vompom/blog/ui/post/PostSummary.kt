package com.vompom.blog.ui.post

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import blog_kmp.composeapp.generated.resources.Res
import blog_kmp.composeapp.generated.resources.ic_folder
import com.vompom.blog.data.model.Category
import com.vompom.blog.data.model.Post
import com.vompom.blog.data.model.PostV2
import com.vompom.blog.data.model.Tag
import com.vompom.blog.ui.OnCategoryClick
import com.vompom.blog.ui.OnPostClick
import com.vompom.blog.ui.OnTagClick
import com.vompom.blog.ui.StatsScene
import com.vompom.blog.ui.component.TagItem
import com.vompom.blog.ui.utils.formatDate
import com.vompom.blog.utils.countChineseChars
import org.jetbrains.compose.resources.painterResource

/**
 *
 * Created by @juliswang on 2025/06/19 21:57
 *
 * @Description
 */

@Composable
fun PostSummary(
    data: Post,
    index: Int,
    scene: Int = StatsScene.DEFAULT,
    onPostClick: OnPostClick = {},
    onTagClick: OnTagClick = {},
    onCategoryClick: OnCategoryClick = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp, vertical = 4.dp)
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(5.dp))
            .border(
                0.dp,
                MaterialTheme.colorScheme.surfaceTint,
                RoundedCornerShape(5.dp)
            )
            .background(MaterialTheme.colorScheme.onSecondary)
            .padding(5.dp)
            .clickable(onClick = { onPostClick(data) })
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Title(scene, data.title, 0, index)
            Spacer(Modifier.height(3.dp))

            if (scene == StatsScene.DEFAULT) {
                TimeAndCategories(index, data.date, data.categories, onCategoryClick)
                Spacer(Modifier.height(5.dp))
                Summary(data.content)
                Spacer(Modifier.height(5.dp))
                Tags(data.tags, onTagClick)
            }
        }
    }
}


@Composable
fun PostSimple(
    data: PostV2,
    index: Int,
    scene: Int = StatsScene.DEFAULT,
    onPostClick: OnPostClick = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp, vertical = 4.dp)
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(5.dp))
            .border(
                0.dp,
                MaterialTheme.colorScheme.surfaceTint,
                RoundedCornerShape(5.dp)
            )
            .background(MaterialTheme.colorScheme.onSecondary)
            .padding(5.dp)
            .clickable(onClick = { onPostClick(Post(title = data.title, url = data.url)) }),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Title(scene, data.title, countChineseChars(data.content), index)
            Spacer(Modifier.height(3.dp))
        }
    }
}

@Composable
private fun Title(scene: Int, title: String, contentLength: Int, index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (scene == StatsScene.CHARACTER) {
            Text(
                text = "[${contentLength}字]",
                fontSize = 10.sp,
                modifier = Modifier.padding(end = 4.dp)
            )
        }
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun TimeAndCategories(
    index: Int,
    date: String,
    categories: List<Category>,
    onCategoryClick: OnCategoryClick,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(22.dp, 12.dp)
                .background(
                    MaterialTheme.colorScheme.background,
                    RoundedCornerShape(5.dp)
                )
        ) {
            Text(
                text = "[$index]",
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Text(
            text = formatDate(date),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
        )

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            categories.forEach { category ->
                CategoryItem(category, onCategoryClick)
            }
        }
    }
}

@Composable
private fun CategoryItem(category: Category, onCategoryClick: OnCategoryClick) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(MaterialTheme.colorScheme.background)
            .clickable(onClick = { onCategoryClick(category) })
            .padding(start = 4.dp, end = 2.dp),
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_folder),
            contentDescription = null,
            modifier = Modifier.size(12.dp),
            tint = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = category.name,
            fontSize = 12.sp,
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 2.dp)
        )
    }
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
            .background(
                MaterialTheme.colorScheme.background,
                RoundedCornerShape(5.dp)
            )
            .padding(8.dp)
    )
}

@Composable
private fun Tags(tags: List<Tag>, onTagClick: OnTagClick) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(bottom = 5.dp)
    ) {
        tags.forEach { tag ->
            TagItem(tag, false, onTagClick)
        }
    }
}
