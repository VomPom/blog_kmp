package com.vompom.blog.platform

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path.Companion.toPath
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual fun createBlogStore(): DataStore<Preferences> = createDataStore("vm_blog")

actual fun creatSettingsStore(): DataStore<Preferences> = createDataStore("vm_settings")

@OptIn(ExperimentalForeignApi::class)
private fun createDataStore(name: String): DataStore<Preferences> {

    val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )

    return PreferenceDataStoreFactory.createWithPath(
        produceFile = { (documentDirectory?.path.plus("/${name}.preferences_pb")).toPath() }
    )
}