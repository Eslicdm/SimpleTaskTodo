package com.eslirodrigues.simpletasktodo.data.repository

import androidx.lifecycle.LiveData
import com.eslirodrigues.simpletasktodo.data.database.TodoDao
import com.eslirodrigues.simpletasktodo.data.model.Todo

class TodoRepository(private val todoDao: TodoDao) {

    val readAllData: LiveData<MutableList<Todo>> = todoDao.readAllData()

    suspend fun addTodo(todo: Todo) {
        todoDao.addTodo(todo)
    }

    suspend fun updateTodo(todo: Todo) {
        todoDao.updateTodo(todo)
    }

    suspend fun deleteAllTodos() {
        todoDao.deleteAllTodos()
    }


}