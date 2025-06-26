package com.vompom.blog.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.vompom.AppConfig
import com.vompom.blog.platform.AppInstallInfo
import com.vompom.blog.ui.component.ContentContainer
import com.vompom.blog.ui.component.ScreenContainer
import com.vompom.blog.ui.theme.ThemeConstants
import com.vompom.blog.utils.TimeUtils
import com.vompom.blog.viewmodel.MineViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by @juliswang on 2025/05/22 19:12
 *
 * @Description
 */

@Preview
@Composable
fun MineScreen(
    title: String,
    onBackClick: OnBackClick,
    onDebugClicked: () -> Unit,
) {
    val viewModel = koinViewModel<MineViewModel>()
    ScreenContainer(title, onBackClick = onBackClick) {
        Info()
        Settings(onDebugClicked, viewModel)
        About()
    }
}

@Composable
fun Info() {
    Row(
        modifier = Modifier.padding(bottom = 8.dp, start = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        AsyncImage(
            modifier = Modifier.size(80.dp)
                .shadow(
                    elevation = 5.dp,
                    shape = CircleShape,
                    spotColor = Color.Black
                )
                .clip(CircleShape)
                .clickable { },
            model = AppConfig.AVATAR,
            contentDescription = AppConfig.AVATAR,
        )
        Column(
            modifier = Modifier.wrapContentSize().padding(start = 10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(AppConfig.BLOG_NAME, style = TextStyle(fontSize = 18.sp))
            Text(AppConfig.BLOG_DESC, style = TextStyle(fontSize = 10.sp, color = MaterialTheme.colorScheme.outline))
        }
    }
}

@Composable
fun Settings(onDebugClicked: () -> Unit, mineViewModel: MineViewModel) {
    val themeState by mineViewModel.appTheme.collectAsState()
    val uiChecked by remember(themeState) { derivedStateOf { themeState == ThemeConstants.DARK } }

    ContentContainer(
        title = "设置",
        titleSize = 15.sp,
        titleWeight = FontWeight.Bold
    ) {
        SettingsItem(
            title = "深色模式",
        ) {
            Switch(
                modifier = Modifier.height(15.dp).graphicsLayer {
                    scaleX = 0.7f
                    scaleY = 0.7f
                },
                checked = uiChecked,
                onCheckedChange = {
                    mineViewModel.saveTheme(
                        if (it)
                            ThemeConstants.DARK
                        else
                            ThemeConstants.NORMAL
                    )
                })
        }
        SettingsItem(title = "Debug") {
            Box(modifier = Modifier.fillMaxWidth().clickable { onDebugClicked() })
        }
    }
}

@Composable
fun About() {
    val appInfo = remember { AppInstallInfo() }
    // 在 Compose 中启动一个协程来获取版本号
    ContentContainer(title = "关于", titleSize = 15.sp, titleWeight = FontWeight.Bold) {
        Column {
            InfoItem("应用名称", AppConfig.BLOG_NAME)
            InfoItem("应用版本", appInfo.getAppVersion())
            InfoItem("更新时间", TimeUtils.timeFormat(appInfo.getUpdateTime()))
        }
    }
}

@Composable
fun SettingsItem(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = TextStyle(fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface),
        )
        content()
    }
}

@Composable
fun InfoItem(
    title: String,
    data: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = TextStyle(fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface),
        )

        Text(
            text = data,
            style = TextStyle(fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface),
        )
    }
}