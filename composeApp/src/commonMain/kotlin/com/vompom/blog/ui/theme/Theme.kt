package com.vompom.blog.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.vompom.blog.platform.StatusAppearanceColor

/**
 * Created by @juliswang on 2025/06/06 20:42
 *
 * @Description
 */
private val LightColors = lightColorScheme(

    /* 主色系 */
    // 主色是在应用程序的屏幕和组件中最常显示的颜色。
    primary = md_theme_light_primary,
    // 用于显示在主色之上的文本和图标的颜色。
    onPrimary = md_theme_light_onPrimary,
    // 容器首选的色调颜色。
    primaryContainer = md_theme_light_primaryContainer,
    // 应用于主色容器之上内容（以及状态变体）的颜色。
    onPrimaryContainer = md_theme_light_onPrimaryContainer,


    /* 次色系 */
    // 辅色提供了更多突出和区分产品的方式。
    secondary = md_theme_light_secondary,
    // 用于显示在辅色之上的文本和图标的颜色。
    onSecondary = md_theme_light_onSecondary,
    // 用于容器的一种色调颜色。
    secondaryContainer = md_theme_light_secondaryContainer,
    // 应用于辅色容器之上内容（以及状态 variants）的颜色。
    onSecondaryContainer = md_theme_light_onSecondaryContainer,


    /* 第三色系 */
    // 可用于平衡主色和辅色，或用于让某个元素（如输入字段）更受关注的第三色。
    tertiary = md_theme_light_tertiary,
    // 用于显示在第三色之上的文本和图标的颜色。
    onTertiary = md_theme_light_onTertiary,
    // 用于容器的一种色调颜色。
    tertiaryContainer = md_theme_light_tertiaryContainer,
    // 应用于第三色容器之上内容（以及状态 variants）的颜色。
    onTertiaryContainer = md_theme_light_onTertiaryContainer,


    /* 背景与表面色 */
    // 错误色用于指示组件中的错误，例如文本字段中的无效文本
    error = md_theme_light_error,
    // 错误容器首选的色调颜色。
    errorContainer = md_theme_light_errorContainer,
    // 用于显示在错误色之上的文本和图标的颜色。
    onError = md_theme_light_onError,
    // 应用于错误容器之上内容（以及状态 variants）的颜色。
    onErrorContainer = md_theme_light_onErrorContainer,


    /* 背景与表面色 */
    // 出现在可滚动内容背后的背景颜色。
    background = md_theme_light_background,
    // 用于显示在背景色之上的文本和图标的颜色。
    onBackground = md_theme_light_onBackground,
    // 影响组件表面（如卡片、面板和菜单）的表面颜色。
    surface = md_theme_light_surface,
    // 用于显示在表面色之上的文本和图标的颜色。
    onSurface = md_theme_light_onSurface,
    // 具有与表面色类似用途的另一种颜色选项。
    surfaceVariant = md_theme_light_surfaceVariant,
    // 	可用于表面之上内容（以及状态 variants）的颜色。
    onSurfaceVariant = md_theme_light_onSurfaceVariant,


    /* 其他 */
    // 用于元素边框的颜色。
    outline = md_theme_light_outline,
    // 一种与反色表面形成良好对比的颜色。
    inverseOnSurface = md_theme_light_inverseOnSurface,
    // 一种与表面色形成鲜明对比的颜色。
    inverseSurface = md_theme_light_inverseSurface,
    // 主色的反色，用于在对比背景上需要主色时
    inversePrimary = md_theme_light_inversePrimary,
    // 表面色的着色，通常用于表面元素的图标或小组件。
    surfaceTint = md_theme_light_surfaceTint,
    // 边框颜色的变体，可能用于更细微的分界线
    outlineVariant = md_theme_light_outlineVariant,
    // 用于遮挡内容的遮罩的颜色。
    scrim = md_theme_light_scrim,
)

private val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)

@Composable
fun VMTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    StatusAppearanceColor(darkTheme)
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = VMTypography,
        shapes = VMShapes,
        content = content
    )
}

object ThemeConstants {
    const val NORMAL = 0
    const val DARK = 1
}
