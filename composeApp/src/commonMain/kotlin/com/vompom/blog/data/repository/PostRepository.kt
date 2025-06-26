package com.vompom.blog.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.vompom.blog.data.api.PostApi
import com.vompom.blog.data.model.PageResponse
import com.vompom.blog.data.model.PostResponse
import com.vompom.blog.data.model.PostV2
import kotlinx.coroutines.flow.Flow

/**
 * Created by @juliswang on 2025/05/27 20:19
 *
 * @Description
 */

class PostRepository(private val postApi: PostApi) : BaseRepository() {

    fun getPostPage(api: String): Flow<PageResponse> = load("postPages-$api") {
        postApi.getPostPage(api) ?: PageResponse()
    }

    fun getPosts(api: String): Flow<PostResponse> = load("post-$api") {
        postApi.getPosts(api) ?: PostResponse()
    }

    fun loadAllPost(): Flow<List<PostV2>> = load("allPosts") {
        postApi.getAllPost()?.data ?: emptyList()
    }

    override fun dataStoreInstance(): DataStore<Preferences> = blogStoreInstance()

}