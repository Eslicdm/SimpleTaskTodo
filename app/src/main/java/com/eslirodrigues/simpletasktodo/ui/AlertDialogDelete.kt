package com.eslirodrigues.simpletasktodo.ui

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eslirodrigues.simpletasktodo.R
import com.eslirodrigues.simpletasktodo.ui.theme.LightBrown
import com.eslirodrigues.simpletasktodo.ui.theme.LightDarkBrown
import com.eslirodrigues.simpletasktodo.viewmodel.TodoViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AlertDialogDelete(showAlertDialogDelete: MutableState<Boolean>) {
    val viewModel = getViewModel<TodoViewModel>()
    val checkBoxState = remember { mutableStateOf(false) }

    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        onDismissRequest = { showAlertDialogDelete.value = false },
        title = {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ){
                Icon(
                    Icons.Default.Delete,
                    tint = Color.White,
                    contentDescription = stringResource(id = R.string.delete))
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = stringResource(id = R.string.delete),
                    style = TextStyle(
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        },
        text = {
            Column {
                Text(
                    text = stringResource(id = R.string.are_you_sure_to_delete),
                    color = Color.White,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Row {
                    Checkbox(
                        checked = checkBoxState.value,
                        onCheckedChange = {
                            checkBoxState.value = it
                            viewModel.saveCheckbox(checkBoxState.value)
                        },
                        colors = CheckboxDefaults.colors(LightDarkBrown),
                    )
                    Text(
                        text = stringResource(id = R.string.dont_ask_me_again),
                        color = Color.White,
                        fontSize = 15.sp
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                modifier = Modifier
                    .padding(
                        start = 5.dp,
                        end = 15.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    ),
                onClick = {
                    viewModel.deleteAllTodos()
                    showAlertDialogDelete.value = false
                }
            ) {
                Text(
                    text = stringResource(id = R.string.confirm).toUpperCase(Locale.current),
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
                    showAlertDialogDelete.value = false
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
fun PreviewAlertDialogDelete() {

}