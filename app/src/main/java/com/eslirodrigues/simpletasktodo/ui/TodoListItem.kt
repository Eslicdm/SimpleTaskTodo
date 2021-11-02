package com.eslirodrigues.simpletasktodo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eslirodrigues.simpletasktodo.data.model.Todo
import com.eslirodrigues.simpletasktodo.ui.theme.LightDarkBrown

@Composable
fun TodoListItem(todo: Todo) {
    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(14.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { /* TODO editTodo */  },
        ) {
            if(!todo.isChecked)  {
                Text(
                    text = todo.todo,
                    modifier = Modifier
                        .width(363.dp)
                        .padding(10.dp),
                    fontSize = 20.sp
                )
            } else {
                Text(
                    text = todo.todo,
                    modifier = Modifier
                        .width(363.dp)
                        .padding(10.dp),
                    fontSize = 20.sp,
                    style = TextStyle(textDecoration = TextDecoration.LineThrough)
                )
            }
            Checkbox(
                checked = todo.isChecked,
                onCheckedChange = {todo.isChecked = !todo.isChecked},
                colors = CheckboxDefaults.colors(LightDarkBrown),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(10.dp, end = 25.dp)
            )
        }
        
    }
}

@Preview
@Composable
fun PreviewTodoListItem() {
    TodoListItem(todo = Todo(1, "Taskaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", true))
}