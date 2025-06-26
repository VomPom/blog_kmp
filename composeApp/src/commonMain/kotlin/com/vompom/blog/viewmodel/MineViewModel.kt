package com.vompom.blog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vompom.blog.data.repository.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 *
 * Created by @juliswang on 2025/06/25 21:17
 *
 * @Description
 */

class MineViewModel(private val repository: SettingsRepository) : ViewModel() {
    val appTheme: StateFlow<Int?> = repository.getTheme().map { it }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = null,
    )

    fun saveTheme(theme: Int) {
        viewModelScope.launch {
            repository.setTheme(theme)
        }
    }
}