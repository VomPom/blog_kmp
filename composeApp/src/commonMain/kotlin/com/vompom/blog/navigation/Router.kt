package com.vompom.blog.navigation

import kotlinx.serialization.Serializable

/**
 *
 * Created by @juliswang on 2025/06/17 21:12
 *
 * @Description
 */
@Serializable
object Routes {
    @Serializable
    class PostType(val api: String, val title: String)

    @Serializable
    class PostDetail(val title: String, val url: String)

    @Serializable
    class Home(val title: String = "")

    @Serializable
    class Debug()
}
