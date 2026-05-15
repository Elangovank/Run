package com.elango.auth.data.di

import com.elango.auth.data.EmailPatternValidator
import com.elango.auth.data.repository.AuthRepositoryImpl
import com.elango.auth.domain.PatternValidator
import com.elango.auth.domain.UserDataValidator
import com.elango.auth.domain.repository.AuthRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}