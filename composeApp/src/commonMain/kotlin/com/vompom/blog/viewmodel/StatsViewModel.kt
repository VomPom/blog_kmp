package com.vompom.blog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vompom.blog.data.model.Category
import com.vompom.blog.data.model.ListDataState
import com.vompom.blog.data.model.PostV2
import com.vompom.blog.data.model.Tag
import com.vompom.blog.data.repository.StatsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Created by @juliswang on 2025/05/28 20:43
 *
 * @Description
 */

class StatsViewModel(private val repository: StatsRepository) : ViewModel() {
    private val _tagsState = MutableStateFlow(ListDataState<Tag>())
    val tagsState: StateFlow<ListDataState<Tag>> = _tagsState.asStateFlow()

    private val _categoriesState = MutableStateFlow(ListDataState<Category>())
    val categoriesState: StateFlow<ListDataState<Category>> = _categoriesState.asStateFlow()

    private val _postsState = MutableStateFlow(ListDataState<PostV2>())
    val postsState: StateFlow<ListDataState<PostV2>> = _postsState.asStateFlow()

    fun loadData() {
        load({ repository.loadAllPost() }, _postsState)
        load({ repository.loadCategories() }, _categoriesState)
        load({ repository.loadTags() }, _tagsState)
    }

    private fun <T> load(
        load: suspend () -> Flow<List<T>>,
        state: MutableStateFlow<ListDataState<T>>,
    ) {
        viewModelScope.launch {
            state.update {
                it.copy(isLoading = true)
            }
            load().collect { result ->
                state.update {
                    it.copy(
                        isLoading = false,
                        data = it.data + result
                    )
                }
            }
        }
    }
}