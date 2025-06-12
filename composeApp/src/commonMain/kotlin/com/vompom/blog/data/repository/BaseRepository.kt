package com.vompom.blog.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

/**
 *
 * Created by @juliswang on 2025/06/12 20:34
 *
 * @Description
 */
// todo use db...
class BaseRepository {
    internal val dataStoreFileName = "vm.blog_db"
    private fun createDataStore(producePath: () -> String): DataStore<Preferences> =
        PreferenceDataStoreFactory.createWithPath(
            produceFile = { producePath().toPath() }
        )

    fun saveData(key: String, value: String) {

    }

    fun getDat(key: String): String? {
        return ""
    }
}