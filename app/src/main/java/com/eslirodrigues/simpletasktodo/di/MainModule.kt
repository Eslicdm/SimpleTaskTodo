package com.eslirodrigues.simpletasktodo.di

import android.app.Application
import androidx.room.Room
import com.eslirodrigues.simpletasktodo.adapter.TodoAdapter
import com.eslirodrigues.simpletasktodo.data.database.TodoDao
import com.eslirodrigues.simpletasktodo.data.database.TodoDatabase
import com.eslirodrigues.simpletasktodo.data.repository.TodoRepository
import com.eslirodrigues.simpletasktodo.viewmodel.TodoViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { TodoAdapter(get(), get()) }

    viewModel { TodoViewModel(get()) }
}

val databaseModule = module {

    fun provideTodoDatabase(application: Application) : TodoDatabase {
        return Room.databaseBuilder(application, TodoDatabase::class.java, "todo_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideTodoDao(database: TodoDatabase): TodoDao {
        return database.todoDao()
    }

    single { provideTodoDatabase(androidApplication()) }
    single { provideTodoDao(get()) }
}

val repositoryModule = module {
    single { TodoRepository(get(), get()) }
}