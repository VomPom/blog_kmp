package com.vompom.blog.platform

/**
 *
 * Created by @juliswang on 2025/06/12 21:21
 *
 * @Description
 */


expect class AppInstallInfo() {
     fun getAppVersion(): String

     fun getUpdateTime(): Long
}