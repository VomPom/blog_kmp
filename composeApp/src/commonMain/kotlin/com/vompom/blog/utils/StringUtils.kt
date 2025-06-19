package com.vompom.blog.utils

import io.ktor.http.*

/**
 *
 * Created by @juliswang on 2025/06/17 21:05
 *
 * @Description
 */

fun encodeStr(data: String): String = Url(data).encodedPath
fun decodeStr(data: String): String =data