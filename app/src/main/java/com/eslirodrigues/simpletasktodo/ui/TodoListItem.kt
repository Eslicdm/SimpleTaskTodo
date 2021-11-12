package com.eslirodrigues.simpletasktodo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eslirodrigues.simpletasktodo.data.model.Todo
import com.eslirodrigues.simpletasktodo.ui.theme.LightDarkBrown
import com.eslirodrigues.simpletasktodo.util.Constants.CHECKBOX_ITEM
import com.eslirodrigues.simpletasktodo.util.Constants.TODO_UPDATE_ITEM
import com.eslirodrigues.simpletasktodo.viewmodel.TodoViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun TodoListItem(todo: Todo) {
    val todoIsChecked = remember { mutableStateOf(false) }
    val showAlertDialogUpdate = remember { mutableStateOf(false) }
    todoIsChecked.value = todo.isChecked
    val viewModel = getViewModel<TodoViewModel>()

    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(14.dp))
    ) {
        Box(modifier = Modifier
            .testTag(TODO_UPDATE_ITEM)
            .clickable {
                showAlertDialogUpdate.value = !showAlertDialogUpdate.value
            }
        ) {
            if (showAlertDialogUpdate.value) AlertDialogUpdate(
                todo = todo,
                showAlertDialogUpdate = showAlertDialogUpdate
            )
            if(!todoIsChecked.value)  {
                Text(
                    text = todo.todo,
                    modifier = Modifier
                        .width(340.dp)
                        .padding(10.dp),
                    fontSize = 20.sp
                )
            } else {
                Text(
                    text = todo.todo,
                    modifier = Modifier
                        .width(340.dp)
                        .padding(10.dp),
                    fontSize = 20.sp,
                    style = TextStyle(textDecoration = TextDecoration.LineThrough)
                )
            }
            Checkbox(
                checked = todoIsChecked.value,
                onCheckedChange = {
                    todoIsChecked.value = it
                    todo.isChecked = todoIsChecked.value
                    viewModel.updateTodo(todo)
                },
                colors = CheckboxDefaults.colors(LightDarkBrown),
                modifier = Modifier
                    .testTag(CHECKBOX_ITEM)
                    .align(Alignment.CenterEnd)
                    .padding(horizontal = 25.dp)
            )
        }
    }
}


@Preview
@Composable
fun PreviewTodoListItem() {
    TodoListItem(todo = Todo(1, "Taskaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", true))
}