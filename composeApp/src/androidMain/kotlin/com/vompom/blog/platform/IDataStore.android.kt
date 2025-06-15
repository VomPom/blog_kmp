package com.vompom.blog.platform

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath
import org.koin.core.context.GlobalContext.get

actual fun createDataStore(): DataStore<Preferences> {
    val context = get().get<Application>()
    val path = context.getExternalFilesDir(null) ?: context.filesDir
    return PreferenceDataStoreFactory.createWithPath(
        produceFile = { (path.absolutePath.plus("/vm_blog.preferences_pb")).toPath() }
    )
}