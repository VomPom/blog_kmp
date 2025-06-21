package com.vompom.blog.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.vompom.blog.platform.createDataStore
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
// todo use db...
open class BaseRepository {
    val dataStore = getDataStoreInstance()

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
    inline fun <reified T> loadLocalData(key: String): Flow<T?> {
        val dataKey = stringPreferencesKey(key)
        return dataStore.data
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
                    Json.decodeFromString<T>(data)
                }
            }
            // !!! take(1) 仅获取第一次值后停止监听
            // 由于 dataStore.data 获取到的是一个热流，如果不关闭的话会对整个域进行阻塞，导致后面可能的一些逻辑代码无法调用
            // 也就是：dataStore.data 获取对应 key 如果在后面不使用 take(1)，那么其他地方如果对 dataStore.data 对应的 key
            // 进行数据修改的时候，对应的订阅者收到的数据也会更新，而使用了take(1)对应的订阅者收到的数据不会被更新。
            .take(1)
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
        crossinline netRequest: suspend () -> T,
    ): Flow<T> {
        return flow {
            loadLocalData<T>(saveKey).collect { result ->
                if (result == null) {
                    val netResult = netRequest()
                    // todo:: optimize ...
//                    saveData(saveKey, Json.encodeToString(netResult))
                    emit(netResult)
                } else {
                    emit(result as T)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: DataStore<Preferences>? = null
        fun getDataStoreInstance(): DataStore<Preferences> {
            return INSTANCE ?: run {
                val instance = createDataStore()
                INSTANCE = instance
                instance
            }
        }
    }
}