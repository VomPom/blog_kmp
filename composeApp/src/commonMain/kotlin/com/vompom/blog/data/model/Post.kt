package com.vompom.blog.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PageResponse(
    val api: String = "",
    val data: PageInfo? = null,
)

@Serializable
data class PageInfo(
    val count: Int = 0,
    val info: Info = Info(),
    val pages: List<Page> = emptyList(),
    val total: Int = 0,
)

@Serializable
data class Info(
    val type: String = "",
    val name: String = "",
    val slug: String = "",
)

@Serializable
data class Page(
    val api: String = "",
    val count: Int = 0,
    val index: Int = 0,
    val info: Info = Info(),
)

@Serializable
data class PostResponse(
    val api: String = "",
    val `data`: PostSummaryList ? = null ,
)

@Serializable
data class SearchResponse(
    val api: String = "",
    val `data`: List<PostV2> = emptyList(),
)


@Serializable
data class PostSummaryList(
    val index: Int = 0,
    val info: Info? = null,
    val posts: List<Post>? = null,
    val total: Int = 0,
)

@Serializable
data class Post(
    val api: String = "",
    val categories: List<Category> = emptyList(),
    val comments: Boolean = false,
    val content: String = "",
    val cover: String? = "",
    val date: String = "",
    val excerpt: String = "",
    val images: List<String> = emptyList(),
    val slug: String = "",
    val tags: List<Tag> = emptyList(),
    val title: String = "",
    val updated: String = "",
    val url: String = "",
)

@Serializable
data class PostV2(
    val api: String = "",
    val comments: Boolean = false,
    val content: String = "",
    val cover: String? = "",
    val date: String = "",
    val excerpt: String = "",
    val images: List<String> = emptyList(),
    val categories: List<String> = emptyList(),
    val slug: String = "",
    val tags: List<String> = emptyList(),
    val title: String = "",
    val updated: String = "",
    val url: String = "",
)

@Serializable
data class Category(
    val api: String,
    val name: String,
    val count: Int = 0,
    val slug: String,
)

@Serializable
data class TagList(
    val api: String,
    val `data`: List<Tag>,
)

@Serializable
data class Tag(
    val api: String,
    val name: String,
    val count: Int = 0,
    val slug: String,
)

@Serializable
data class CategoryList(
    val api: String,
    val `data`: List<Category>,
)
