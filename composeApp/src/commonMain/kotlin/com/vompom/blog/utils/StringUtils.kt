package com.vompom.blog.utils

import com.vompom.blog.data.model.PostV2
import io.ktor.http.*

/**
 *
 * Created by @juliswang on 2025/06/17 21:05
 *
 * @Description
 */

fun encodeStr(data: String): String = Url(data).encodedPath
fun decodeStr(data: String): String = data

/**
 * 计算所有文章的中文字符总数
 */
fun calLetters(allPost: List<PostV2>): Int {
    return allPost.sumOf { post ->
        val cnt = calLetter(post)
        println("字数统计 [${cnt}]-> ${post.title}")
        cnt
    }
}

/**
 * 计算单篇文章的中文字符数
 */
fun calLetter(post: PostV2): Int {
    return countChineseChars(post.content)
}

/**
 * 统计字符串中的中文字符数量
 */
fun countChineseChars(str: String?): Int {
    if (str.isNullOrEmpty()) return 0

    var count = 0
    val length = str.length
    var i = 0

    // 手动遍历避免迭代器开销
    while (i < length) {
        val char = str[i]
        // 使用整数比较替代范围检查
        if (char.code >= 0x4e00 && char.code <= 0x9fa5) {
            count++
        }
        i++
    }
    return count
}