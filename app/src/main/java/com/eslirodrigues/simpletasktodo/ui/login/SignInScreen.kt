package com.eslirodrigues.simpletasktodo.ui.login

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.eslirodrigues.simpletasktodo.R
import com.eslirodrigues.simpletasktodo.ui.theme.DarkGray
import com.eslirodrigues.simpletasktodo.ui.theme.LightBrown
import com.eslirodrigues.simpletasktodo.ui.theme.LightDarkBrown
import com.eslirodrigues.simpletasktodo.util.ScreenNav
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.compose.get

@Composable
fun SignInScreen(navController: NavController = get()) {
    var inputEmail by remember { mutableStateOf("") }
    var iconEmailState by remember { mutableStateOf(false) }
    var inputPassword by remember { mutableStateOf("") }
    var iconPasswordState by remember { mutableStateOf(false) }
    var iconShowPasswordState by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        LightBrown,
                        LightDarkBrown
                    )
                )
            )
            .fillMaxSize()
    ) {
        Image(
            painterResource(R.drawable.ic_simpletasktodobrand),
            contentDescription = stringResource(id = R.string.app_name),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 120.dp)
                .width(80.dp)
                .height(80.dp)
        )
        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 10.dp, vertical = 50.dp)
                .width(400.dp)
                .height(400.dp),
            elevation = 2.dp,
            shape = RoundedCornerShape(corner = CornerSize(14.dp))
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 5.dp)
                        .align(Alignment.CenterHorizontally),
                    text = stringResource(id = R.string.enter_your_account),
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    modifier = Modifier
                        .onFocusChanged {
                            iconEmailState = !iconEmailState
                        }
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .fillMaxWidth(),
                    value = inputEmail,
                    onValueChange = {
                        inputEmail = it
                    },
                    label = {
                        Text(text = stringResource(id = R.string.email))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = stringResource(id = R.string.add),
                            tint = if (iconEmailState) DarkGray else LightDarkBrown
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )

                OutlinedTextField(
                    modifier = Modifier
                        .onFocusChanged {
                            iconPasswordState = !iconPasswordState
                        }
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth(),
                    value = inputPassword,
                    onValueChange = {
                        inputPassword = it
                    },
                    label = {
                        Text(text = stringResource(id = R.string.password))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = stringResource(id = R.string.add),
                            tint = if (iconPasswordState) DarkGray else LightDarkBrown
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            singIn(
                                navController = navController,
                                context = context,
                                email = inputEmail,
                                password = inputPassword
                            )
                        }
                    ),
                    visualTransformation = if (iconShowPasswordState) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { iconShowPasswordState = !iconShowPasswordState }) {
                            Icon(
                                imageVector = if (iconShowPasswordState) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Password Toggle"
                            )
                        }
                    }
                )
                Text(
                    fontSize = 14.sp,
                    color = DarkGray,
                    text = stringResource(id = R.string.forgot_pass),
                    modifier = Modifier
                        .padding(end = 12.dp, top = 8.dp)
                        .align(Alignment.End)
                        .clickable {
                            navController.navigate(
                                route = ScreenNav.ForgotPassword.route
                            )
                        }
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(115.dp)
                        .padding(vertical = 32.dp, horizontal = 46.dp)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(backgroundColor = LightDarkBrown),
                    shape = RoundedCornerShape(corner = CornerSize(50.dp)),
                    onClick = {
                        singIn(
                            navController = navController,
                            context = context,
                            email = inputEmail,
                            password = inputPassword
                        )
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.log_in).toUpperCase(Locale.current)
                    )
                }
                Text(
                    color = LightDarkBrown,
                    text = stringResource(id = R.string.enter_without_login),
                    modifier = Modifier
                        .padding(end = 10.dp, bottom = 25.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            navController.navigate(
                                route = ScreenNav.Todo.route,
                            )
                        }
                )
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = stringResource(id = R.string.dont_have_account),
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        color = LightDarkBrown,
                        text = stringResource(id = R.string.sign_up),
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .align(Alignment.CenterVertically)
                            .clickable {
                                navController.navigate(
                                    route = ScreenNav.SignUp.route
                                )
                            }
                    )
                }
            }
        }
    }
}

fun singIn(navController: NavController, context: Context, email: String, password: String) {
    when {
        TextUtils.isEmpty(
            email.trim { it <= ' ' }) -> {
            Toast.makeText(
                context,
                "Please Enter Email",
                Toast.LENGTH_SHORT
            ).show()
        }

        TextUtils.isEmpty(
            password.trim { it <= ' ' }) -> {
            Toast.makeText(
                context,
                "Please Enter Password",
                Toast.LENGTH_SHORT
            ).show()
        }
        else -> {
            email.trim { it <= ' ' }
            password.trim { it <= ' ' }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            context,
                            "Login is successful",
                            Toast.LENGTH_SHORT
                        ).show()

                        navController.navigate(
                            route = ScreenNav.Todo.route,
                        )
                    } else {
                        Toast.makeText(
                            context,
                            task.exception!!.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}

@Preview
@Composable
fun PreviewSignInScreen() {

}