package com.eslirodrigues.simpletasktodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.eslirodrigues.simpletasktodo.ui.TodoScreen
import com.eslirodrigues.simpletasktodo.ui.theme.SimpleTaskTodoTheme

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleTaskTodoTheme {
                TodoScreen()
            }
        }
    }
}