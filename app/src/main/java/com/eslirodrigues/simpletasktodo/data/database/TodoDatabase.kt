package com.eslirodrigues.simpletasktodo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eslirodrigues.simpletasktodo.data.model.Todo

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao() : TodoDao

    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getInstance(context: Context) : TodoDatabase {
            synchronized(this) {
                var instance: TodoDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        TodoDatabase::class.java,
                        "todo_database"
                    ).build()
                }
                return instance
            }
        }

    }
}