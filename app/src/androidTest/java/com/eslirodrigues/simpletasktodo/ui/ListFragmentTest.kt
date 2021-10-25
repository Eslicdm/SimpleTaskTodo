package com.eslirodrigues.simpletasktodo.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.eslirodrigues.simpletasktodo.R
import com.eslirodrigues.simpletasktodo.data.database.TodoDao
import com.eslirodrigues.simpletasktodo.data.database.TodoDatabase
import com.eslirodrigues.simpletasktodo.data.model.Todo
import com.eslirodrigues.simpletasktodo.di.roomTestModule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@MediumTest
@RunWith(AndroidJUnit4::class)
class ListFragmentTest : KoinTest {

    private val todoDatabase: TodoDatabase by inject()
    private val todoDao: TodoDao by inject()

    @Before()
    fun before() {
        loadKoinModules(roomTestModule)
    }

    @After
    fun after() {
        todoDatabase.close()
        stopKoin()
    }

    @Test
    fun addNewTodo_listIsNotNull() {
        val todo = Todo(id = 1, todo = "car", isChecked = true)

        runBlocking { todoDao.addTodo(todo) }

        assertThat(todoDao.readAllData()).isNotNull()
    }
}