package com.vompom.blog.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PageList(
    val api: String,
    val data: Data? = null,
)

@Serializable
data class Data(
    val count: Int,
    val info: Info,
    val pages: List<Page>,
    val total: Int
)

@Serializable
data class Info(
    val type: String
)

@Serializable
data class Page(
    val api: String,
    val count: Int,
    val index: Int,
    val info: Info
)

@Serializable
data class PostDetail(
    val api: String,
    val `data`: Data
)

@Serializable
data class PostSummaryList(
    val index: Int,
    val info: Info,
    val posts: List<Post>,
    val total: Int
)

@Serializable
data class Post(
    val api: String,
    val categories: List<Category>,
    val comments: Boolean,
    val content: String,
    val cover: String,
    val date: String,
    val excerpt: String,
    val images: List<String>,
    val slug: String,
    val tags: List<Tag>,
    val title: String,
    val updated: String,
    val url: String
)

@Serializable
data class Category(
    val api: String,
    val name: String,
    val count: Int,
    val slug: String
)

@Serializable
data class TagList(
    val api: String,
    val `data`: List<Tag>
)

@Serializable
data class Tag(
    val api: String,
    val name: String,
    val count: Int,
    val slug: String
)

@Serializable
data class CategoryList(
    val api: String,
    val `data`: List<Category>
)
