package com.vompom.blog.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 *
 * Created by @juliswang on 2025/06/12 20:47
 *
 * @Description
 */

object TimeUtils {
    fun timeFormat(timestamp: Long): String {
        return Instant.fromEpochMilliseconds(timestamp)
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .toString()
            .replace("T", " ") // 将 T 替换为空格
            .substringBefore(".") // 移除毫秒部分
    }
}