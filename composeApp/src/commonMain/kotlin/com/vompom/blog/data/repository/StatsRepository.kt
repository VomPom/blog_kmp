package com.vompom.blog.data.repository

import com.vompom.blog.data.api.PostApi
import com.vompom.blog.data.api.StatsApi
import com.vompom.blog.data.model.Category
import com.vompom.blog.data.model.PostV2
import com.vompom.blog.data.model.Tag
import kotlinx.coroutines.flow.Flow

/**
 * Created by @juliswang on 2025/05/28 15:18
 *
 * @Description
 */

class StatsRepository(private val api: StatsApi, private val postApi: PostApi) : BaseRepository() {
    fun loadAllPost(isRefresh: Boolean): Flow<List<PostV2>> = load("allPosts", isRefresh) {
        postApi.getAllPost()?.data ?: emptyList()
    }

    fun loadTags(isRefresh: Boolean): Flow<List<Tag>> = load("tag", isRefresh) {
        api.loadTags().data
    }

    fun loadCategories(isRefresh: Boolean): Flow<List<Category>> = load("category", isRefresh) {
        api.loadCategories().data
    }

}