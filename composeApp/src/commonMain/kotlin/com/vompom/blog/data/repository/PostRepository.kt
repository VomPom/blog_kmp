package com.vompom.blog.data.repository

import com.vompom.blog.data.api.PostApi
import com.vompom.blog.data.model.PageList

/**
 * Created by @juliswang on 2025/05/27 20:19
 *
 * @Description
 */

class PostRepository(private val postApi: PostApi) {
    suspend fun getData(): PageList = postApi.getData()
}