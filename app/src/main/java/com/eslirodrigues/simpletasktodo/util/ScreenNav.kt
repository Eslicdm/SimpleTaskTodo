package com.eslirodrigues.simpletasktodo.util

sealed class ScreenNav(val route: String) {
    object Todo: ScreenNav(route = "todo_screen")
    object SignUp: ScreenNav(route = "sign_up_screen")
    object SignIn: ScreenNav(route = "sign_in_screen")
    object ForgotPassword: ScreenNav(route = "forgot_password_screen")
}
