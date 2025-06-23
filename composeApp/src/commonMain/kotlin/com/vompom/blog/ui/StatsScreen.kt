package com.vompom.blog.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Downloading
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vompom.blog.data.model.*
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
    onBackClick: OnBackClick,
    onTagClick: OnTagClick,
    onCategoryClick: OnCategoryClick,
    onStatsClick: OnStatsClick,
) {
    val viewModel = koinViewModel<StatsViewModel>()
    val tagsState by viewModel.tagsState.collectAsState()
    val categoriesState by viewModel.categoriesState.collectAsState()
    val postsState by viewModel.postsState.collectAsState()
    val letterCntState by viewModel.letterCntState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }
    ScreenContainer("统计", onBackClick, Icons.Filled.Refresh) {
        Summary(
            onStatsClick,
            postsState,
            tagsState,
            categoriesState,
            letterCntState
        )
        ContentContainer("#分类") {
            Categories(categoriesState, onCategoryClick)
        }
        ContentContainer("#标签") {
            Tags(tagsState, onTagClick)
        }
    }
}


@Composable
fun Summary(
    onStatsClick: OnStatsClick,
    allPostUiState: ListDataState<PostV2>,
    tagUiState: ListDataState<Tag>,
    categoryUiState: ListDataState<Category>,
    letterCntState: Int,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StatsLabel(
            count = allPostUiState.data.size,
            label = "篇",
            isLoading = allPostUiState.isLoading,
            onClick = {
                if (allPostUiState.data.isNotEmpty()) {
                    onStatsClick(StatsScene.ALL_POST)
                }
            }
        )

        // 字数统计
        StatsLabel(
            count = letterCntState,
            label = "字数",
            isLoading = allPostUiState.isLoading, // 假设字数不需要加载状态
            onClick = {
                if (allPostUiState.data.isNotEmpty()) {
                    onStatsClick(StatsScene.CHARACTER)
                }
            }
        )

        // 分类统计
        StatsLabel(
            count = categoryUiState.data.size,
            label = "分类",
            isLoading = categoryUiState.isLoading,
            onClick = { }
        )

        // 标签统计
        StatsLabel(
            count = tagUiState.data.size,
            label = "标签",
            isLoading = tagUiState.isLoading,
            onClick = { }
        )
    }
}

@Composable
fun Tags(state: ListDataState<Tag>, onTagClick: OnTagClick) {
    StatsFlowRow {
        state.data.forEach { tag ->
            TagItem(tag, true, onTagClick)
        }
    }
}

@Composable
fun Categories(state: ListDataState<Category>, onCategoryClick: OnCategoryClick) {
    StatsFlowRow {
        state.data.forEach { item ->
            CategoryItem(item) {
                println("onclick item:$item")
                onCategoryClick(item)
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
    isLoading: Boolean,
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
            if (isLoading) {
                Icon(
                    imageVector = Icons.Default.Downloading,
                    contentDescription = "Loading"
                )
            } else {
                Text(
                    text = count.toString(),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 15.sp
                )
            }
        }
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 12.sp,
            style = TextStyle.Default.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
        )
    }
}

enum class StatsScene {
    DEFAULT,
    ALL_POST,
    CHARACTER,
}

typealias OnPostClick = (Post) -> Unit
typealias OnCategoryClick = (Category) -> Unit
typealias OnTagClick = (Tag) -> Unit
typealias OnStatsClick = (StatsScene) -> Unit

@Preview()
@Composable
fun StatsItemPre() {
    PreviewWrapper {
        ContentContainer("标签") {
            Text("2132")
        }
    }

}