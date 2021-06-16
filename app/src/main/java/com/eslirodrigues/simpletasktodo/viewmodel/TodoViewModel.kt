package com.eslirodrigues.simpletasktodo.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.eslirodrigues.simpletasktodo.data.database.TodoDatabase
import com.eslirodrigues.simpletasktodo.data.model.Todo
import com.eslirodrigues.simpletasktodo.data.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<MutableList<Todo>>
    private val repository: TodoRepository

    init {
        val todoDao = TodoDatabase.getInstance(application).todoDao()
        repository = TodoRepository(todoDao, application)
        readAllData = repository.readAllData
    }

    val readCheckboxDataStore = repository.readCheckboxDataStore.asLiveData()

    fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTodo(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodo(todo)
        }
    }

    fun deleteAllTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTodos()
        }
    }

    fun saveCheckbox(isChecked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveCheckbox(isChecked)
        }
    }
}