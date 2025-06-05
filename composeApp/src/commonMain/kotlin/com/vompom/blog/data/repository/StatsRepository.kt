package com.vompom.blog.data.repository

import com.vompom.blog.data.api.StatsApi
import com.vompom.blog.data.model.Category
import com.vompom.blog.data.model.Tag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by @juliswang on 2025/05/28 15:18
 *
 * @Description
 */

class StatsRepository(private val api: StatsApi) {
    fun loadTags(): Flow<List<Tag>> {
        return flow {
            emit(api.loadTags().data)
        }
    }

    fun loadCategories(): Flow<List<Category>> {
        return flow {
            emit(api.loadCategories().data)
        }
    }

}