package com.vompom.blog.platform

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath
import org.koin.core.context.GlobalContext.get

actual fun createBlogStore(): DataStore<Preferences> = createDataStore("vm_blog")

actual fun creatSettingsStore(): DataStore<Preferences> = createDataStore("vm_settings")

private fun createDataStore(name: String): DataStore<Preferences> {
    val context = get().get<Application>()
    val path = context.getExternalFilesDir(null) ?: context.filesDir
    return PreferenceDataStoreFactory.createWithPath(
        produceFile = { (path.absolutePath.plus("/${name}.preferences_pb")).toPath() }
    )
}