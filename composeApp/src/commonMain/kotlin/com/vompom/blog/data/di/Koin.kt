package com.vompom.blog.data.di

import com.vompom.blog.data.api.PostApi
import com.vompom.blog.data.api.PostApiImpl
import com.vompom.blog.data.api.StatsApi
import com.vompom.blog.data.api.StatsApiImpl
import com.vompom.blog.data.repository.PostRepository
import com.vompom.blog.data.repository.SettingsRepository
import com.vompom.blog.data.repository.StatsRepository
import com.vompom.blog.viewmodel.MainViewModel
import com.vompom.blog.viewmodel.MineViewModel
import com.vompom.blog.viewmodel.PostViewModel
import com.vompom.blog.viewmodel.StatsViewModel
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

/**
 * Created by @juliswang on 2025/05/27 20:01
 *
 * @Description
 */

val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient() {
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Any)
            }
            install(Logging) {
                level = LogLevel.HEADERS
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
            }
        }
    }
    single<PostApi> { PostApiImpl(get()) }
    single { PostRepository(get()) }

    single<StatsApi> { StatsApiImpl(get()) }
    single { StatsRepository(get(), get()) }
    single { SettingsRepository() }

}
val viewModelModule = module {
    factoryOf(::PostViewModel)
    factoryOf(::StatsViewModel)
    factoryOf(::MineViewModel)
    factoryOf(::MainViewModel)
}

fun initKoin(additionAppDeclaration: KoinAppDeclaration) {
    startKoin {
        modules(
            dataModule,
            viewModelModule
        )
        additionAppDeclaration()
    }
}