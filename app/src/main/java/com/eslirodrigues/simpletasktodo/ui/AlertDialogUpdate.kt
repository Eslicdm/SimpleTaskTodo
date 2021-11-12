package com.eslirodrigues.simpletasktodo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eslirodrigues.simpletasktodo.R
import com.eslirodrigues.simpletasktodo.data.model.Todo
import com.eslirodrigues.simpletasktodo.ui.theme.LightBrown
import com.eslirodrigues.simpletasktodo.ui.theme.LightDarkBrown
import com.eslirodrigues.simpletasktodo.util.Constants.ALERT_UPDATE_CONFIRM
import com.eslirodrigues.simpletasktodo.util.Constants.ALERT_UPDATE_TEXT_FIELD
import com.eslirodrigues.simpletasktodo.viewmodel.TodoViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AlertDialogUpdate(todo: Todo, showAlertDialogUpdate: MutableState<Boolean>) {
    val viewModel = getViewModel<TodoViewModel>()
    val inputTask = remember { mutableStateOf("") }
    inputTask.value = todo.todo
    val focusManager = LocalFocusManager.current

    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        onDismissRequest = { showAlertDialogUpdate.value = false },
        text = {
            TextField(
                modifier = Modifier.testTag(ALERT_UPDATE_TEXT_FIELD),
                value = inputTask.value,
                onValueChange = {
                    inputTask.value = it
                    todo.todo = inputTask.value
                },
                placeholder = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.add),
                        tint = Color.White
                    )
                    Text(
                        text = stringResource(id = R.string.task),
                        modifier = Modifier
                            .width(250.dp),
                        textAlign = TextAlign.Center,
                    )
                },
                shape = RoundedCornerShape(14.dp),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    placeholderColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = LightDarkBrown
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        viewModel.updateTodo(todo)
                        focusManager.clearFocus()
                        inputTask.value = ""
                        showAlertDialogUpdate.value = false
                    }
                )
            )
        },
        confirmButton = {
            TextButton(
                modifier = Modifier
                    .testTag(ALERT_UPDATE_CONFIRM)
                    .padding(
                        start = 5.dp,
                        end = 15.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    ),
                onClick = {
                    viewModel.updateTodo(todo)
                    focusManager.clearFocus()
                    inputTask.value = ""
                    showAlertDialogUpdate.value = false
                }
            ) {
                Text(
                    text = stringResource(id = R.string.save).toUpperCase(Locale.current),
                    color = Color.White,
                )
            }
        },
        dismissButton = {
            TextButton(
                modifier = Modifier
                    .padding(
                        start = 15.dp,
                        end = 5.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    ),
                onClick = {
                    showAlertDialogUpdate.value = false
                }
            ) {
                Text(
                    text = stringResource(id = R.string.cancel).toUpperCase(Locale.current),
                    color = Color.White,
                )

            }
        },
        backgroundColor = LightBrown,
        contentColor = Color.White,
        shape = RoundedCornerShape(25.dp)
    )
}


@Preview
@Composable
fun PreviewAlertDialogUpdate() {

}