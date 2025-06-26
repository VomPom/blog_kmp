package com.vompom.blog.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.vompom.blog.platform.creatSettingsStore
import com.vompom.blog.platform.createBlogStore
import kotlinx.coroutines.flow.*
import kotlinx.io.IOException
import kotlinx.serialization.json.Json
import kotlin.concurrent.Volatile

/**
 *
 * Created by @juliswang on 2025/06/12 20:34
 *
 * @Description
 */
abstract class BaseRepository() {
    val dataStore = dataStoreInstance()

    suspend fun saveData(key: String, value: String) {
        val dataKey = stringPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[dataKey] = value
        }
    }

    /**
     * 从本地文件里面获取数据
     *
     * @param T
     * @param key
     * @return
     */
    inline fun <reified T> loadLocalData(key: String, isJson: Boolean = true): Flow<T?> {
        val dataKey = stringPreferencesKey(key)
        val result = dataStore.data
            .catch { exception ->
                // dataStore.data throws an IOException when an error is encountered when reading data
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val data: String? = preferences[dataKey]
                if (data == null) {
                    null
                } else {
                    if (isJson) Json.decodeFromString<T>(data) else (data as T)
                }
            }
        // !!! take(1) 仅获取第一次值后停止监听
        // 由于 dataStore.data 获取到的是一个热流，如果不关闭的话会对整个域进行阻塞，导致后面可能的一些逻辑代码无法调用
        // 也就是：dataStore.data 获取对应 key 如果在后面不使用 take(1)，那么其他地方如果对 dataStore.data 对应的 key
        // 进行数据修改的时候，对应的订阅者收到的数据也会更新，而使用了take(1)对应的订阅者收到的数据不会被更新。
        return if (isHotFlow())
            result
        else
            result.take(1)
    }

    /**
     * 加载数据，优先加载本地的数据，没获取到再获取网络数据
     *
     * @param T
     * @param saveKey
     * @param netRequest    网络请求获取数据
     * @return
     */
    inline fun <reified T> load(
        saveKey: String,
        isRefresh: Boolean = false,
        crossinline netRequest: suspend () -> T,
    ): Flow<T> {
        val fetchAndSave: suspend FlowCollector<T>.() -> Unit = {
            val netResult = netRequest()
            val encodeStr = Json.encodeToString(netResult)
            if (encodeStr != "{}") {
                saveData(saveKey, Json.encodeToString(netResult))
            }
            emit(netResult)
        }

        return flow {
            if (isRefresh) {
                fetchAndSave()
                return@flow
            }
            loadLocalData<T>(saveKey).collect { result ->
                if (result == null) {
                    fetchAndSave()
                } else {
                    emit(result as T)
                }
            }
        }
    }

    abstract fun dataStoreInstance(): DataStore<Preferences>

    /**
     * 设置里面的数据需要 热流（需要一处改变，其他处实时更新的功能）
     *
     * @return
     */
    fun isHotFlow(): Boolean = dataStoreInstance() == SETTING_INSTANCE

    companion object {
        @Volatile
        private var INSTANCE: DataStore<Preferences>? = null
        fun blogStoreInstance(): DataStore<Preferences> {
            return INSTANCE ?: run {
                val instance = createBlogStore()
                INSTANCE = instance
                instance
            }
        }

        /**
         * 用于存放 settings
         */
        @Volatile
        private var SETTING_INSTANCE: DataStore<Preferences>? = null
        fun settingsStoreInstance(): DataStore<Preferences> {
            return SETTING_INSTANCE ?: run {
                val instance = creatSettingsStore()
                SETTING_INSTANCE = instance
                instance
            }
        }
    }
}