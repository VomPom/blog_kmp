package com.vompom.blog.platform

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.vompom.AppConfig
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import net.harawata.appdirs.AppDirsFactory
import okio.Path.Companion.toPath

actual fun createDataStore(): DataStore<Preferences> {
    val userDataDir: String = AppDirsFactory.getInstance()
        .getUserDataDir("com.vompom.blog", "", AppConfig.AUTHOR)

    val path = Path(userDataDir)
    with(SystemFileSystem) { if (!exists(path)) createDirectories(path) }

    return PreferenceDataStoreFactory.createWithPath(
        produceFile = { (path.toString().plus("/vm_blog.preferences_pb")).toPath() }
    )
}