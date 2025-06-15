package com.vompom.blog.data.repository

import com.vompom.blog.data.api.PostApi
import com.vompom.blog.data.model.PageList
import kotlinx.coroutines.flow.Flow

/**
 * Created by @juliswang on 2025/05/27 20:19
 *
 * @Description
 */

class PostRepository(private val postApi: PostApi) : BaseRepository() {
    suspend fun getData(): PageList = postApi.getData()


    fun loadCategories(): Flow<PageList> = load("pageList") {
        postApi.getData()
    }
}