package com.elango.auth.presentation

import androidx.lifecycle.viewmodel.compose.viewModel
import com.elango.auth.presentation.login.LoginViewModel
import com.elango.auth.presentation.register.RegisterViewmodel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val authViewModelModule = module {
    viewModelOf(::RegisterViewmodel)
    viewModelOf(::LoginViewModel)

}