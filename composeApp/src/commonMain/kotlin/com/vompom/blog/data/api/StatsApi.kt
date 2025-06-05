package com.vompom.blog.data.api

import com.vompom.AppConfig
import com.vompom.blog.data.model.CategoryList
import com.vompom.blog.data.model.TagList
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.utils.io.*

/**
 *
 * Created by @juliswang on 2025/05/28 20:35
 *
 * @Description
 */


interface StatsApi {
    suspend fun loadTags(): TagList
    suspend fun loadCategories(): CategoryList
}

class StatsApiImpl(private val client: HttpClient) : StatsApi {
    companion object {
        private const val TAG_URL = "${AppConfig.BASE_URL}/api/tags.json"
        private const val CATEGORY_URL = "${AppConfig.BASE_URL}/api/categories.json"
    }

    override suspend fun loadTags(): TagList {
        return try {
            client.get(TAG_URL).body()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            TagList("", emptyList())
        }
    }

    override suspend fun loadCategories(): CategoryList {
        return try {
            client.get(CATEGORY_URL).body()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            CategoryList("", emptyList())
        }
    }
}
