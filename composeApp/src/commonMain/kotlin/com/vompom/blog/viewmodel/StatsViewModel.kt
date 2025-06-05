package com.vompom.blog.viewmodel

import androidx.lifecycle.ViewModel
import com.vompom.blog.data.model.Category
import com.vompom.blog.data.model.Tag
import com.vompom.blog.data.repository.StatsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by @juliswang on 2025/05/28 20:43
 *
 * @Description
 */

class StatsViewModel(private val repository: StatsRepository) : ViewModel() {

    fun loadTags(): Flow<List<Tag>> = repository.loadTags()
    fun loadCategories(): Flow<List<Category>> = repository.loadCategories()

}