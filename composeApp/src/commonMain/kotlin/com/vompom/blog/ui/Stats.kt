package com.vompom.blog.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vompom.blog.ui.component.CategoryItem
import com.vompom.blog.ui.component.TagItem
import com.vompom.blog.ui.component.VMToolbar
import com.vompom.blog.viewmodel.StatsViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by @juliswang on 2025/05/22 19:12
 *
 * @Description
 */

@Composable
fun StatsScreen() {
    val viewModel = koinViewModel<StatsViewModel>()
    Content(viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(viewModel: StatsViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { VMToolbar("统计", Icons.Filled.Refresh) },
        contentColor = MaterialTheme.colorScheme.surface,

        ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding(),
                    start = 10.dp,
                    end = 10.dp
                ),
        ) {
            StatsItem("#分类") {
                Categories(viewModel)
            }
            StatsItem("#标签") {
                Tags(viewModel)
            }
        }
    }
}

@Composable
fun StatsItem(title: String, content: @Composable ColumnScope.() -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(5)
            )
            .background(
                color = MaterialTheme.colorScheme.onSecondary,
                shape = RoundedCornerShape(3)
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
                    style = TextStyle.Default.copy(fontSize = 20.sp, color = Color.DarkGray)
                )
                content()
            }
        }
    }
}

@Composable
fun Tags(viewModel: StatsViewModel) {
    val tags by viewModel.loadTags().collectAsStateWithLifecycle(emptyList())
    StatsFlowRow {
        tags.forEach { tag ->
            TagItem(tag, true) {
                println("onclick item:$tag")
            }
        }
    }
}

@Composable
fun Categories(viewModel: StatsViewModel) {
    val categoryList by viewModel.loadCategories().collectAsStateWithLifecycle(emptyList())
    StatsFlowRow {
        categoryList.forEach { item ->
            CategoryItem(item) {
                println("onclick item:$item")
            }
        }
    }
}

@Composable
fun StatsFlowRow(content: @Composable FlowRowScope.() -> Unit) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 16.dp)
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        content()
    }
}

@Preview()
@Composable
fun StatsItemPre() {
    StatsItem("标签") {
        Text("2132")
    }
}