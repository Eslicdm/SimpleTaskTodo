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
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.eslirodrigues.simpletasktodo.R
import com.eslirodrigues.simpletasktodo.ui.theme.DarkGray
import com.eslirodrigues.simpletasktodo.ui.theme.LightBrown
import com.eslirodrigues.simpletasktodo.ui.theme.LightDarkBrown
import com.eslirodrigues.simpletasktodo.util.ScreenNav
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.compose.get

@Composable
fun SignUpScreen(navController: NavController = get()) {
    var inputEmail by remember { mutableStateOf("") }
    var iconEmailState by remember { mutableStateOf(false) }
    var inputPassword by remember { mutableStateOf("") }
    var iconPasswordState by remember { mutableStateOf(false) }
    var iconShowPasswordState by remember { mutableStateOf(false) }
    var inputConfirmPassword by remember { mutableStateOf("") }
    var iconConfirmPasswordState by remember { mutableStateOf(false) }
    var iconConfirmShowPasswordState by remember { mutableStateOf(false) }
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
                    text = stringResource(id = R.string.create_account),
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
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
                OutlinedTextField(
                    modifier = Modifier
                        .onFocusChanged {
                            iconConfirmPasswordState = !iconConfirmPasswordState
                        }
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth(),
                    value = inputConfirmPassword,
                    onValueChange = {
                        inputConfirmPassword = it
                    },
                    label = {
                        Text(text = stringResource(id = R.string.confirm_password))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = stringResource(id = R.string.add),
                            tint = if (iconConfirmPasswordState) DarkGray else LightDarkBrown
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            signUp(
                                navController = navController,
                                context = context,
                                email = inputEmail,
                                password = inputPassword,
                                confirmPassword = inputConfirmPassword
                            )
                        }
                    ),
                    visualTransformation = if (iconConfirmShowPasswordState) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { iconConfirmShowPasswordState = !iconConfirmShowPasswordState }) {
                            Icon(
                                imageVector = if (iconConfirmShowPasswordState) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Password Toggle"
                            )
                        }
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
                        signUp(
                            navController = navController,
                            context = context,
                            email = inputEmail,
                            password = inputPassword,
                            confirmPassword = inputConfirmPassword
                        )
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.log_in).toUpperCase(Locale.current)
                    )
                }
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = stringResource(id = R.string.already_account),
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        color = LightDarkBrown,
                        text = stringResource(id = R.string.log_in),
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .align(Alignment.CenterVertically)
                            .clickable {
                                navController.popBackStack(
                                    route = ScreenNav.SignIn.route,
                                    inclusive = false
                                )
                            }
                    )
                }
            }
        }
    }
}

fun signUp(navController: NavController, context: Context, email: String, password: String, confirmPassword: String) {
    when {
        TextUtils.isEmpty(
            email.trim { it <= ' ' }) -> {
            Toast.makeText(
                context,
                "Please Enter email",
                Toast.LENGTH_SHORT
            ).show()
        }

        TextUtils.isEmpty(
            password.trim { it <= ' ' }) -> {
            Toast.makeText(
                context,
                "Please Enter password",
                Toast.LENGTH_SHORT
            ).show()
        }
        else -> {
            email.trim { it <= ' ' }
            password.trim { it <= ' ' }
            confirmPassword.trim { it <= ' ' }

            if(password != confirmPassword) {
                Toast.makeText(context, "Password mismatch", Toast.LENGTH_SHORT)
                    .show()
            }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            context,
                            "You were registered successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        navController.navigate(
                            ScreenNav.Todo.route
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
fun PreviewSignUpScreen() {

}