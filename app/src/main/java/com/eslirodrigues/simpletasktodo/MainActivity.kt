package com.eslirodrigues.simpletasktodo

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eslirodrigues.simpletasktodo.ui.TodoScreen
import com.eslirodrigues.simpletasktodo.ui.login.ForgotPasswordScreen
import com.eslirodrigues.simpletasktodo.ui.login.SignInScreen
import com.eslirodrigues.simpletasktodo.ui.login.SignUpScreen
import com.eslirodrigues.simpletasktodo.ui.theme.LightBrown
import com.eslirodrigues.simpletasktodo.ui.theme.LightDarkBrown
import com.eslirodrigues.simpletasktodo.ui.theme.SimpleTaskTodoTheme
import com.eslirodrigues.simpletasktodo.util.ScreenNav
import kotlinx.coroutines.delay

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
                    startDestination = ScreenNav.SplashScreen.route
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
                    composable(route = ScreenNav.SplashScreen.route) {
                        SplashScreen(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(1500L)
        navController.navigate(ScreenNav.Todo.route) {
            popUpTo(ScreenNav.SplashScreen.route) {
                inclusive = true
            }
        }
    }
    Column(
        modifier = Modifier
            .background(LightDarkBrown)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.scale(scale.value),
            painter = painterResource(id = R.drawable.ic_simpletasktodobrand),
            contentDescription = stringResource(id = R.string.app_name)
        )
    }
}