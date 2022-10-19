package de.haspanext.multimoduleexample.application

import android.app.Application
import de.haspanext.authentication.di.authenticationKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MehrwerteApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MehrwerteApplication)
            modules(authenticationKoinModule)
        }
    }
}