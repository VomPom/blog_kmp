package com.vompom.blog.data.api

import com.vompom.AppConfig
import com.vompom.blog.data.model.PageList
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.utils.io.*

/**
 * Created by @juliswang on 2025/05/27 20:05
 *
 * @Description
 */

interface PostApi {
    suspend fun getData(): PageList
}

class PostApiImpl(private val client: HttpClient) : PostApi {
    companion object {
        private const val API_URL = "${AppConfig.BASE_URL}/api/posts.json"
    }

    override suspend fun getData(): PageList {
        return try {
            client.get(API_URL).body()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            PageList("", null)
        }
    }
}
