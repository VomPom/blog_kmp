package com.vompom.blog

/**
 *
 * Created by @juliswang on 2025/07/09 21:07
 *
 * @Description
 */

object SystemUtils {
    val isWindows = System.getProperty("os.name").startsWith("Win")

    val isLinux = System.getProperty("os.name").startsWith("Linux")

    val isMac = System.getProperty("os.name").startsWith("Mac")
}