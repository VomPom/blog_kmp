package com.vompom.blog

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform