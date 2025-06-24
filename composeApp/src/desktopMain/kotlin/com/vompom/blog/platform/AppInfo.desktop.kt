package com.vompom.blog.platform

import java.io.File

actual class AppInstallInfo actual constructor() {
    actual fun getAppVersion(): String {
        return try {
            val clazz = this::class.java
            val resources = clazz.classLoader.getResources("META-INF/MANIFEST.MF")
            while (resources.hasMoreElements()) {
                val manifest = resources.nextElement().openStream().use {
                    java.util.jar.Manifest(it).mainAttributes
                }
                val version = manifest.getValue("Implementation-Version") ?: continue
                if (version.isNotBlank()) return version
            }
            "Unknown"
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }

    actual fun getUpdateTime(): Long {
        return try {
            val codeSource = this::class.java.protectionDomain.codeSource
            val jarFile = File(codeSource.location.toURI())
            jarFile.lastModified()
        } catch (e: Exception) {
            System.currentTimeMillis()
        }
    }
}