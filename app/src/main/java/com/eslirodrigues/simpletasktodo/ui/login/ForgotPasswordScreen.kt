package com.eslirodrigues.simpletasktodo.ui.login

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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
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
import org.koin.androidx.compose.get

@Composable
fun ForgotPasswordScreen(navController: NavController = get()) {
    var inputEmail by remember { mutableStateOf("") }
    var iconEmailState by remember { mutableStateOf(false) }

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
                .padding(top = 100.dp)
                .align(Alignment.Center)
                .padding(horizontal = 10.dp, vertical = 50.dp)
                .width(400.dp)
                .height(330.dp),
            elevation = 2.dp,
            shape = RoundedCornerShape(corner = CornerSize(14.dp))
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    fontSize = 22.sp,
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 5.dp)
                        .align(Alignment.CenterHorizontally),
                    text = stringResource(id = R.string.forgot_pass_title),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 10.dp)
                        .align(Alignment.CenterHorizontally),
                    text = stringResource(id = R.string.forgot_pass_description),
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
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            /* TODO Submit */
                        }
                    )
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(115.dp)
                        .padding(vertical = 32.dp, horizontal = 46.dp)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(backgroundColor = LightDarkBrown),
                    shape = RoundedCornerShape(corner = CornerSize(50.dp)),
                    onClick = { /* TODO Submit */ }
                ) {
                    Text(
                        text = stringResource(id = R.string.submit).toUpperCase(Locale.current)
                    )
                }
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
                                    ScreenNav.SignUp.route
                                )
                            }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewForgotPasswordScreen() {

}