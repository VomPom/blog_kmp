package com.vompom.blog.data.model

/**
 *
 * Created by @juliswang on 2025/06/19 21:28
 *
 * @Description
 */

data class ListDataState<T>(
    val data: List<T> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)

