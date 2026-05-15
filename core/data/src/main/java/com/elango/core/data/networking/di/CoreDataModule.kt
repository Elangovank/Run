package com.elango.core.data.networking.di

import com.elango.core.data.DataStoreSessionStorage
import com.elango.core.data.networking.HttpClientFactory
import com.elango.core.domain.SessionStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val coreDataModule = module {
    single {
        HttpClientFactory(get()).build()
    }
    singleOf(::DataStoreSessionStorage).bind<SessionStorage>()
}
