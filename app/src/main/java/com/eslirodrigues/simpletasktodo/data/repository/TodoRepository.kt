package com.eslirodrigues.simpletasktodo.data.repository

import androidx.lifecycle.LiveData
import com.eslirodrigues.simpletasktodo.data.database.TodoDao
import com.eslirodrigues.simpletasktodo.data.model.Todo

class TodoRepository(private val todoDao: TodoDao) {

    val readAllData: LiveData<List<Todo>> = todoDao.readAllData()

    suspend fun addTodo(todo: Todo) {
        todoDao.addTodo(todo)
    }

    suspend fun updateTodo(todo: Todo) {
        todoDao.updateTodo(todo)
    }

    suspend fun updateCheckbox(id: Int, checkbox: Boolean) {
        todoDao.updateCheckbox(id, checkbox)
    }

    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }

    suspend fun deleteAllTodos() {
        todoDao.deleteAllTodos()
    }


}