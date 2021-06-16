package com.eslirodrigues.simpletasktodo.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import com.eslirodrigues.simpletasktodo.data.database.TodoDao
import com.eslirodrigues.simpletasktodo.data.model.Todo
import com.eslirodrigues.simpletasktodo.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.PREF_NAME)

class TodoRepository(
    private val todoDao: TodoDao,
    private val context: Context
) {

    private object PreferenceKeys {
        val checkboxDeleteKey = booleanPreferencesKey(Constants.CHECKBOX_DELETE_KEY)
    }

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


    suspend fun saveCheckbox(isChecked: Boolean) {
        context.dataStore.edit { preference ->
            preference[PreferenceKeys.checkboxDeleteKey] = isChecked
        }
    }

    val readCheckboxDataStore: Flow<Boolean> = context.dataStore.data.catch { exception ->
        if (exception is IOException) {
            Log.d("DataStore", exception.message.toString())
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preference ->
        val isChecked = preference[PreferenceKeys.checkboxDeleteKey] ?: false
        isChecked
    }

}