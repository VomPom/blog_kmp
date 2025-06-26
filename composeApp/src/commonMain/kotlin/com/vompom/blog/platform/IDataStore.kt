package com.vompom.blog.platform

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

/**
 *
 * Created by @juliswang on 2025/06/13 11:21
 *
 * @Description
 */

expect fun createBlogStore(): DataStore<Preferences>

expect fun creatSettingsStore(): DataStore<Preferences>