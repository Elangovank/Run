package com.elango.run.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.elango.run.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_pref")

val appModule = module {
    single<DataStore<Preferences>> {
        androidApplication().dataStore
    }

    singleOf(::MainViewModel)
}
