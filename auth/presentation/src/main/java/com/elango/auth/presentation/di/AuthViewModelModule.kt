package com.elango.auth.presentation.di

import com.elango.auth.presentation.login.LoginViewModel
import com.elango.auth.presentation.register.RegisterViewmodel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val authViewModelModule = module {
    viewModelOf(::RegisterViewmodel)
    viewModelOf(::LoginViewModel)

}