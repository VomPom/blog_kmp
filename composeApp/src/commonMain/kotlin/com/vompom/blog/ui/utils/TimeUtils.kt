package com.vompom.blog.ui.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 *
 * Created by @juliswang on 2025/06/20 21:25
 *
 * @Description
 */

fun formatDate(isoDate: String): String {
    return try {
        val instant = Instant.parse(isoDate)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        "${localDateTime.year}" +
                "-${localDateTime.monthNumber.padStart(2, '0')}" +
                "-${localDateTime.dayOfMonth.padStart(2, '0')}"
    } catch (_: Exception) {
        isoDate // 解析失败时返回原字符串
    }
}

private fun Int.padStart(length: Int, padChar: Char): String {
    return this.toString().padStart(length, padChar)
}