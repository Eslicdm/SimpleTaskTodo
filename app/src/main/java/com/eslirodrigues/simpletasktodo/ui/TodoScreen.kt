package com.eslirodrigues.simpletasktodo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eslirodrigues.simpletasktodo.R
import com.eslirodrigues.simpletasktodo.data.model.Todo
import com.eslirodrigues.simpletasktodo.ui.theme.LightBrown
import com.eslirodrigues.simpletasktodo.ui.theme.LightDarkBrown

@Composable
fun TodoScreen() {
    var showMenu by remember { mutableStateOf(false) }
    val todoList = listOf(
        Todo(1, "Task 1 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", true),
        Todo(2, "Task 2 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", false),
        Todo(3, "Task 3 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", true),
        Todo(4, "Task 4 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", true),
        Todo(5, "Task 5 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", false),
        Todo(6, "Task 5 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", false),
        Todo(7, "Task 5 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", false),
        Todo(8, "Task 5 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", false),
        Todo(9, "Task 5 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", false),
        Todo(10, "Task 5 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", false),
        Todo(11, "Task 5 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", false)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.app_name), color = Color.White)
                        },
                backgroundColor = LightDarkBrown,
                navigationIcon = {
                    Image(
                        painterResource(R.drawable.ic_simpletasktodobrand),
                        contentDescription = stringResource(id = R.string.app_name),
                        modifier = Modifier.padding(6.dp)
                    ) },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, stringResource(id = R.string.more), tint = Color.White)
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false },
                        modifier = Modifier.background(Color.White)
                    ) {
                        DropdownMenuItem(onClick = { /*TODO navToLoginScreen*/ }) {
                            Text(text = "Log in")
                        }
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
                FloatingActionButton(
                    backgroundColor = LightDarkBrown,
                    onClick = { /*TODO */ }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(id = R.string.add), tint = Color.White)
                }
        }
    ) {
        Column(
            modifier = Modifier
                .background(LightBrown)
                .fillMaxSize()
        ) {
            LazyColumn {
                items(todoList) { todoListItem ->
                    TodoListItem(todo = todoListItem)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewTodoScreen() {
    TodoScreen()
}
