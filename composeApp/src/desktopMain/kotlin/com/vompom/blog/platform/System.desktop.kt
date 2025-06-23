package com.vompom.blog.platform

actual fun getCurrentThread(): String {
    return Thread.currentThread().name
}