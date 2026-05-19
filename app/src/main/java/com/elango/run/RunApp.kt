package com.elango.run

import android.app.Application
import com.elango.auth.data.di.authDataModule
import com.elango.auth.presentation.di.authViewModelModule
import com.elango.core.data.networking.di.coreDataModule
import com.elango.run.di.appModule
import com.elango.run.presentation.di.runViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

import timber.log.Timber

class RunApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@RunApp)
            modules(
                authDataModule,
                authViewModelModule,
                appModule,
                coreDataModule,
                runViewModelModule
            )
        }
    }
}