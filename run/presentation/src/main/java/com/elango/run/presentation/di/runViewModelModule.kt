package com.elango.run.presentation.di

import com.elango.run.presentation.active_run.ActiveRunViewModel
import com.elango.run.presentation.run_overview.RunOverviewViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val runViewModelModule = module {
    viewModelOf(::RunOverviewViewModel)
    viewModelOf(::ActiveRunViewModel)
}