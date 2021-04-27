package com.eslirodrigues.simpletasktodo.model

data class Todo(
    val todo: String,
    var isChecked: Boolean = false
)
