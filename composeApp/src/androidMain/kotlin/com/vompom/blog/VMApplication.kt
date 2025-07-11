package com.vompom.blog

import android.app.Application
import com.vompom.blog.data.di.initKoin
import org.koin.android.ext.koin.androidContext

/**
 *
 * Created by @juliswang on 2025/05/27 20:56
 *
 * @Description
 */

class VMApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@VMApplication)
        }
    }
}