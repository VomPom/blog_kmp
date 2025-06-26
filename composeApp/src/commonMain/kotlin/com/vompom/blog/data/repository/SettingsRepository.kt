package com.vompom.blog.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 *
 * Created by @juliswang on 2025/06/25 21:17
 *
 * @Description
 */

class SettingsRepository : BaseRepository() {

    companion object Companion {
        const val KEY_THEME = "key_theme"
    }

    fun getTheme(): Flow<Int> = flow {
        loadLocalData<String>(KEY_THEME, false).collect { result ->
            if (result == null) {
                emit(0)
            } else {
                emit(result.toInt())
            }
        }
    }

    suspend fun setTheme(theme: Int) {
        saveData(KEY_THEME, theme.toString())
    }

    override fun dataStoreInstance(): DataStore<Preferences> = settingsStoreInstance()
}