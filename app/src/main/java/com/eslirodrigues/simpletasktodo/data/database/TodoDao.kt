package com.eslirodrigues.simpletasktodo.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.eslirodrigues.simpletasktodo.data.model.Todo

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Query("UPDATE todo_table SET isChecked = :check WHERE id = :id")
    suspend fun updateCheckbox(id: Int, check: Boolean)

    @Query("DELETE FROM todo_table WHERE isChecked = 1")
    suspend fun deleteAllTodos()

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun readAllData(): LiveData<MutableList<Todo>>
}