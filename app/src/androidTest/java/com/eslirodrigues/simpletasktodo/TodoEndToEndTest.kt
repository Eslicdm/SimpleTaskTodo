package com.eslirodrigues.simpletasktodo

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.eslirodrigues.simpletasktodo.ui.TodoScreen
import com.eslirodrigues.simpletasktodo.ui.login.ForgotPasswordScreen
import com.eslirodrigues.simpletasktodo.ui.login.SignInScreen
import com.eslirodrigues.simpletasktodo.ui.login.SignUpScreen
import com.eslirodrigues.simpletasktodo.ui.theme.SimpleTaskTodoTheme
import com.eslirodrigues.simpletasktodo.util.Constants
import com.eslirodrigues.simpletasktodo.util.Constants.ALERT_UPDATE_CONFIRM
import com.eslirodrigues.simpletasktodo.util.Constants.ALERT_UPDATE_TEXT_FIELD
import com.eslirodrigues.simpletasktodo.util.Constants.CHECKBOX_ITEM
import com.eslirodrigues.simpletasktodo.util.Constants.FAB_TODO_TAG
import com.eslirodrigues.simpletasktodo.util.Constants.ICON_DELETE
import com.eslirodrigues.simpletasktodo.util.Constants.TEXT_FIELD_ADD_TAG
import com.eslirodrigues.simpletasktodo.util.Constants.TODO_UPDATE_ITEM
import com.eslirodrigues.simpletasktodo.util.ScreenNav
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalAnimationApi
@RunWith(AndroidJUnit4::class)
class TodoEndToEndTest {

    @get:Rule()
    val composeRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalComposeUiApi
    @Before
    fun setUp() {
        composeRule.setContent {
            SimpleTaskTodoTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ScreenNav.Todo.route
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

    @Test
    fun addTodo_todoEdited() {
        // Add task
        composeRule.onNodeWithTag(FAB_TODO_TAG).performClick()
        composeRule.onNodeWithTag(TEXT_FIELD_ADD_TAG).performTextInput("hello")
        composeRule.onNodeWithTag(FAB_TODO_TAG).performClick()
        composeRule.onNodeWithText("hello").assertIsDisplayed()

        // Update task
        composeRule.onNodeWithText("hello").performClick()
        composeRule.onNodeWithTag(ALERT_UPDATE_TEXT_FIELD).performTextInput(" friend")
        composeRule.onNodeWithTag(ALERT_UPDATE_CONFIRM).performClick()
        composeRule.onNodeWithText("hello friend").assertIsDisplayed()

        // Delete task
        composeRule.onNodeWithTag(CHECKBOX_ITEM).performClick()
        composeRule.onNodeWithTag(ICON_DELETE).performClick()
        composeRule.onNodeWithText("hello friend").assertDoesNotExist()
    }
}