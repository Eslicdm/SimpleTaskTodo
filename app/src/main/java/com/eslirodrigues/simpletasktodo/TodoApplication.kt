package com.eslirodrigues.simpletasktodo

import android.app.Application
import com.eslirodrigues.simpletasktodo.di.databaseModule
import com.eslirodrigues.simpletasktodo.di.mainModule
import com.eslirodrigues.simpletasktodo.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TodoApplication)
            modules(
                mainModule,
                databaseModule,
                repositoryModule
            )
        }
    }
}