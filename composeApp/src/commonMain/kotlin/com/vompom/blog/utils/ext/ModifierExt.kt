package com.vompom.blog.utils.ext

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 *
 * Created by @juliswang on 2024/12/27 17:47
 *
 * @Description
 */

/**
 * 虚实分割线
 * 实线虚线长度都为10f 根据实际需求更改下方PathEffect里面的float参数数据
 **/
internal fun Modifier.dashedDivider(strokeWidth: Dp, color: Color) = drawBehind {
    drawIntoCanvas {
        val paint = Paint()
            .apply {
                this.strokeWidth = strokeWidth.toPx()
                this.color = color
                style = PaintingStyle.Stroke
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            }
        it.drawLine(
            Offset(0f, size.height / 2),
            Offset(size.width, size.height / 2),
            paint
        )
    }
}


/**
 * 虚线边框
 * @param width 虚线宽度
 * @param radius 边框角度
 * @param color 边框颜色
 * 虚实间隔也是写死的10f
 **/
internal fun Modifier.dashedBorder(width: Dp = 1.dp, radius: Dp = 1.dp, color: Color = Color.Red) =
    drawBehind {
        drawIntoCanvas {
            val paint = Paint()
                .apply {
                    strokeWidth = width.toPx()
                    this.color = color
                    style = PaintingStyle.Stroke
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                }
            it.drawRoundRect(
                width.toPx(),
                width.toPx(),
                size.width - width.toPx(),
                size.height - width.toPx(),
                radius.toPx(),
                radius.toPx(),
                paint
            )
        }
    }
