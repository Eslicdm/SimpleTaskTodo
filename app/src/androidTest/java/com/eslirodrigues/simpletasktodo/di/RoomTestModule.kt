package com.eslirodrigues.simpletasktodo.di

import android.app.Application
import androidx.room.Room
import com.eslirodrigues.simpletasktodo.data.database.TodoDatabase
import org.koin.dsl.module
import org.koin.android.ext.koin.androidApplication

val roomTestModule = module {
    fun provideFakeDatabase(application: Application) =
        Room.inMemoryDatabaseBuilder(application, TodoDatabase::class.java)
            .allowMainThreadQueries()
            .build()

    single { provideFakeDatabase(androidApplication()) }
}