package com.vompom.blog.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vompom.blog.data.model.Category
import com.vompom.blog.data.model.Tag
import com.vompom.blog.ui.component.CategoryItem
import com.vompom.blog.ui.component.ContentContainer
import com.vompom.blog.ui.component.ScreenContainer
import com.vompom.blog.ui.component.TagItem
import com.vompom.blog.ui.utils.PreviewWrapper
import com.vompom.blog.viewmodel.StatsViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by @juliswang on 2025/05/22 19:12
 *
 * @Description
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreen(
    onTagClicked: OnTagClicked,
    onCategoryClicked: OnCategoryClicked,
) {
    val viewModel = koinViewModel<StatsViewModel>()
    val categoryList by viewModel.loadCategories().collectAsStateWithLifecycle(emptyList())
    val tags by viewModel.loadTags().collectAsStateWithLifecycle(emptyList())

    ScreenContainer("统计", Icons.Filled.Refresh) {
        Summary(tags, categoryList)

        ContentContainer("#分类") {
            Categories(categoryList, onCategoryClicked)
        }
        ContentContainer("#标签") {
            Tags(tags, onTagClicked)
        }
    }
}


@Composable
fun Summary(tags: List<Tag>, categories: List<Category>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // 文章统计
        StatsLabel(
            count = 0, // postPage.count,
            label = "篇",
//            state = loadState,
            onClick = {
//                onNavigateToPostList(allPosts, PostItemScene.ALL_POST)
            }
        )

        // 字数统计
        StatsLabel(
            count = 0, //calLetters(allPosts),
            label = "字数",
//            state = LoadState.SUCCESS, // 假设字数不需要加载状态
            onClick = {
//                val sortedData = allPosts.sortedByDescending { calLetter(it) }
//                onNavigateToSortedPosts(sortedData)
            }
        )

        // 分类统计
        StatsLabel(
            count = categories.size,
            label = "分类",
//            state = loadState,
            onClick = { }
        )

        // 标签统计
        StatsLabel(
            count = tags.size,
            label = "标签",
//            state = loadState,
            onClick = { }
        )
    }
}

@Composable
fun Tags(tags: List<Tag>, onTagClicked: OnTagClicked) {
    StatsFlowRow {
        tags.forEach { tag ->
            TagItem(tag, true) {
                println("onclick item:$tag")
                onTagClicked(tag)
            }
        }
    }
}

@Composable
fun Categories(categoryList: List<Category>, onCategoryClicked: OnCategoryClicked) {
    StatsFlowRow {
        categoryList.forEach { item ->
            CategoryItem(item) {
                println("onclick item:$item")
                onCategoryClicked(item)
            }
        }
    }
}

@Composable
fun StatsFlowRow(content: @Composable FlowRowScope.() -> Unit) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        content()
    }
}

@Composable
fun StatsLabel(
    count: Int,
    label: String,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.height(15.dp),
            contentAlignment = Alignment.Center
        ) {
//            when (state) {
//                LoadState.LOADING -> {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_refresh),
//                        contentDescription = "Loading",
//                        modifier = Modifier.size(15.dp),
//                        tint = MaterialTheme.colors.onBackground
//                    )
//                }

//                else -> {
            Text(
                text = count.toString(),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 15.sp
            )

        }
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 12.sp,
            style = TextStyle.Default.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
        )
    }
}

typealias OnCategoryClicked = (Category) -> Unit
typealias OnTagClicked = (Tag) -> Unit

@Preview()
@Composable
fun StatsItemPre() {
    PreviewWrapper {
        ContentContainer("标签") {
            Text("2132")
        }
    }

}