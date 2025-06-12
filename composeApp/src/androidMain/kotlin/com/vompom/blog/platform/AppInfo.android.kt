package com.vompom.blog.platform

import android.app.Application
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import org.koin.core.context.GlobalContext.get


actual class AppInstallInfo actual constructor() {
    private val context = get().get<Application>()
    private val packageInfo by lazy {
        context.packageManager.getPackageInfo(context.packageName, 0)
    }

    actual fun getAppVersion(): String {
        return try {
            val packageManager: PackageManager = context.packageManager
            val packageInfo: PackageInfo = packageManager.getPackageInfo(context.packageName, 0)
            packageInfo.versionName ?: "Unknown"
        } catch (e: PackageManager.NameNotFoundException) {
            "Error: ${e.message}"
        }
    }

    actual fun getInstallTime(): Long = packageInfo.firstInstallTime
    actual fun getUpdateTime(): Long = packageInfo.lastUpdateTime

}