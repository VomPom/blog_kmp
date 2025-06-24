package com.vompom.blog.data.repository

import com.vompom.blog.data.api.PostApi
import com.vompom.blog.data.model.PageResponse
import com.vompom.blog.data.model.PostResponse
import com.vompom.blog.data.model.PostV2
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock

/**
 * Created by @juliswang on 2025/05/27 20:19
 *
 * @Description
 */

class PostRepository(private val postApi: PostApi) : BaseRepository() {

    fun getUpdateTime(): Flow<String?> = loadLocalData<String?>("updateTime")
    suspend fun setUpdate() {
        saveData("updateTime", Clock.System.now().toEpochMilliseconds().toString())
    }

    fun getPostPage(api: String): Flow<PageResponse> = load("postPages-$api") {
        postApi.getPostPage(api) ?: PageResponse()
    }

    fun getPosts(api: String): Flow<PostResponse> = load("post-$api") {
        postApi.getPosts(api) ?: PostResponse()
    }

    fun loadAllPost(): Flow<List<PostV2>> = load("allPosts") {
        postApi.getAllPost()?.data ?: emptyList()
    }

}