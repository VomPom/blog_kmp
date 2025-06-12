package com.vompom.blog.data.di

import com.vompom.blog.data.api.PostApi
import com.vompom.blog.data.api.PostApiImpl
import com.vompom.blog.data.api.StatsApi
import com.vompom.blog.data.api.StatsApiImpl
import com.vompom.blog.data.repository.PostRepository
import com.vompom.blog.data.repository.StatsRepository
import com.vompom.blog.viewmodel.PostViewModel
import com.vompom.blog.viewmodel.StatsViewModel
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
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
        HttpClient {
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Any)
            }
        }
    }
    single<PostApi> { PostApiImpl(get()) }
    single { PostRepository(get()) }

    single<StatsApi> { StatsApiImpl(get()) }
    single { StatsRepository(get()) }

}
val viewModelModule = module {
    factoryOf(::PostViewModel)
    factoryOf(::StatsViewModel)
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