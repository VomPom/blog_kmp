package com.vompom.blog.data.api

import com.vompom.AppConfig
import com.vompom.blog.data.model.PageResponse
import com.vompom.blog.data.model.PostResponse
import com.vompom.blog.data.model.SearchResponse
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
    suspend fun getAllPost(): SearchResponse?
    suspend fun getPostPage(api: String): PageResponse?
    suspend fun getPosts(api: String): PostResponse?
}

class PostApiImpl(private val client: HttpClient) : PostApi {
    override suspend fun getAllPost(): SearchResponse? = request<SearchResponse>(getUrl("api/search.json"))

    override suspend fun getPostPage(api: String): PageResponse? = request<PageResponse>(getUrl(api))

    override suspend fun getPosts(api: String): PostResponse? = request<PostResponse>(getUrl(api))

    private suspend inline fun <reified T> request(url: String): T? {
        return try {
            client.get(url).body()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            null
        }
    }

    private fun getUrl(path: String): String = "${AppConfig.BASE_URL}/${path}"
}
