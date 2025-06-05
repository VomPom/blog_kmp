package com.vompom.blog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vompom.blog.data.model.PageList
import com.vompom.blog.data.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

/**
 * Created by @juliswang on 2025/05/27 20:18
 *
 * @Description
 */

class PostViewModel(private val repository: PostRepository) : ViewModel() {
    fun getData() {
        viewModelScope.launch {
            latestNews.flowOn(Dispatchers.Default)
                .catch { exception ->

                }
                .collect { favoriteNews ->
                }
        }
    }

    private val latestNews: Flow<PageList> = flow {
        //网络请求
        val latestNews = repository.getData()
        //返回结果
        emit(latestNews)
    }

}