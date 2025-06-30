package com.vompom.blog.platform

actual class AppInstallInfo actual constructor() {
    actual fun getAppVersion(): String {
        return ""
    }


    actual fun getUpdateTime(): Long {
        return 0
    }
}