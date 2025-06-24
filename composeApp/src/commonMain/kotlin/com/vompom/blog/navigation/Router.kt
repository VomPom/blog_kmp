package com.vompom.blog.navigation

import com.vompom.blog.ui.StatsScene
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
    class PostType(
        val api: String = "",
        val title: String = "",
        val scene: Int = StatsScene.DEFAULT,
    ) {
        fun isDefault(): Boolean = this.scene == StatsScene.DEFAULT
    }

    @Serializable
    class PostDetail(val title: String, val url: String, val content: String = "")

    @Serializable
    class Home(val title: String = "")

    @Serializable
    class Debug()
}
