package com.eslirodrigues.simpletasktodo.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eslirodrigues.simpletasktodo.R
import com.eslirodrigues.simpletasktodo.data.model.Todo
import com.eslirodrigues.simpletasktodo.ui.theme.LightBrown
import com.eslirodrigues.simpletasktodo.ui.theme.LightDarkBrown
import com.eslirodrigues.simpletasktodo.util.Constants.FAB_TODO_TAG
import com.eslirodrigues.simpletasktodo.util.Constants.ICON_DELETE
import com.eslirodrigues.simpletasktodo.util.Constants.TEXT_FIELD_ADD_TAG
import com.eslirodrigues.simpletasktodo.util.ScreenNav
import com.eslirodrigues.simpletasktodo.viewmodel.TodoViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun TodoScreen(navController: NavController = get()) {
    val scrollState = rememberLazyListState()
    var showMenu by remember { mutableStateOf(false) }
    val showAlertDialogDelete = remember { mutableStateOf(false) }
    var showAddTask by remember { mutableStateOf(false) }
    var inputTask by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val viewModel = getViewModel<TodoViewModel>()
    val checkOpenDialog = viewModel.readCheckboxDataStore.observeAsState().value
//    val keyboardController = LocalSoftwareKeyboardController.current
    val todoList = viewModel.readAllData.observeAsState(listOf()).value
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

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
                    IconButton(
                        modifier = Modifier.testTag(ICON_DELETE),
                        onClick = {
                            if (checkOpenDialog == true) {
                                viewModel.deleteAllTodos()
                            } else {
                                showAlertDialogDelete.value = !showAlertDialogDelete.value
                            }
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Delete, stringResource(id = R.string.delete_done), tint = Color.White)
                    }
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, stringResource(id = R.string.more), tint = Color.White)
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false },
                        modifier = Modifier.background(Color.White)
                    ) {
                        if (currentUser != null) {
                            DropdownMenuItem(
                                onClick = {
                                    auth.signOut()
                                    navController.popBackStack(
                                        route = ScreenNav.SignIn.route, false
                                    )
                                }
                            ) {
                                Text(text = "Log out")
                            }
                        } else {
                            DropdownMenuItem(
                                onClick = {
                                    navController.navigate(
                                        route = ScreenNav.SignIn.route
                                    )
                                }
                            ) {
                                Text(text = "Log in")
                            }
                        }
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            Row {
                AnimatedVisibility(visible = showAddTask) {
                    TextField(
                        modifier = Modifier
                            .testTag(TEXT_FIELD_ADD_TAG)
                            .width(300.dp)
                            .padding(end = 16.dp),
                        value = inputTask,
                        onValueChange = {
                            inputTask = it
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
                                viewModel.addTodo(Todo(todo = inputTask))
                                focusManager.clearFocus()
                                inputTask = ""
                                showAddTask = !showAddTask
                            }
                        )
                    )
                }

                FloatingActionButton(
                    modifier = Modifier.testTag(FAB_TODO_TAG),
                    backgroundColor = LightDarkBrown,
                    onClick = {
                        showAddTask = !showAddTask
                        if(inputTask.isNotEmpty()) {
                            viewModel.addTodo(Todo(todo = inputTask))
                            inputTask = ""
                        }
                    }
                ) {
                    if(showAddTask) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = stringResource(id = R.string.add),
                            tint = Color.White
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(id = R.string.add),
                            tint = Color.White
                        )
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .background(LightBrown)
                .fillMaxSize()
        ) {
            if(showAlertDialogDelete.value) AlertDialogDelete(showAlertDialogDelete)
            LazyColumn(state = scrollState) {
                items(todoList) { todoListItem ->
                    TodoListItem(todo = todoListItem)
                }
            }
        }
    }
}


@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun PreviewTodoScreen() {
    TodoScreen()
}
