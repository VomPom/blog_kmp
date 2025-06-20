package com.vompom.blog.data.repository

import com.vompom.blog.data.api.PostApi
import com.vompom.blog.data.api.StatsApi
import com.vompom.blog.data.model.Category
import com.vompom.blog.data.model.Post
import com.vompom.blog.data.model.Tag
import kotlinx.coroutines.flow.Flow

/**
 * Created by @juliswang on 2025/05/28 15:18
 *
 * @Description
 */

class StatsRepository(private val api: StatsApi, private val postApi: PostApi) : BaseRepository() {
    fun getAllPost(): Flow<List<Post>> = load("tag") {
        postApi.getAllPost()?.data ?: emptyList()
    }

    fun loadTags(): Flow<List<Tag>> = load("tag") {
        api.loadTags().data
    }

    fun loadCategories(): Flow<List<Category>> = load("category") {
        api.loadCategories().data
    }

}