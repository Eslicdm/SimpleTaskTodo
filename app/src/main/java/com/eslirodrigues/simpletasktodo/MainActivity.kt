package com.eslirodrigues.simpletasktodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eslirodrigues.simpletasktodo.ui.TodoScreen
import com.eslirodrigues.simpletasktodo.ui.login.ForgotPasswordScreen
import com.eslirodrigues.simpletasktodo.ui.login.SignInScreen
import com.eslirodrigues.simpletasktodo.ui.login.SignUpScreen
import com.eslirodrigues.simpletasktodo.ui.theme.SimpleTaskTodoTheme
import com.eslirodrigues.simpletasktodo.util.ScreenNav

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleTaskTodoTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = ScreenNav.SignIn.route
                ) {
                    composable(route = ScreenNav.Todo.route) {
                        TodoScreen(navController = navController)
                    }
                    composable(route = ScreenNav.SignIn.route) {
                        SignInScreen(navController = navController)
                    }
                    composable(route = ScreenNav.SignUp.route) {
                        SignUpScreen(navController = navController)
                    }
                    composable(route = ScreenNav.ForgotPassword.route) {
                        ForgotPasswordScreen(navController = navController)
                    }
                }
            }
        }
    }
}