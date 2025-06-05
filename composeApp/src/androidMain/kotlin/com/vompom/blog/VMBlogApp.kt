package com.vompom.blog

import android.app.Application
import com.vompom.blog.data.di.initKoin

/**
 *
 * Created by @juliswang on 2025/05/27 20:56
 *
 * @Description
 */

class VMBlogApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}