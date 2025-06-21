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
    if (str.isNullOrEmpty()) {
        return 0
    }

    val chineseRegex = Regex("""[\u4e00-\u9fa5]""") // 匹配中文字符的正则
    return chineseRegex.findAll(str).count()
}