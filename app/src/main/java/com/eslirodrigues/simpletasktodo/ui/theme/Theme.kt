package com.eslirodrigues.simpletasktodo.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.eslirodrigues.simpletasktodo.ui.theme.DarkGray
import com.eslirodrigues.simpletasktodo.ui.theme.LightBlue

private val LightColorPalette = lightColors(
    primary = LightDarkBrown,
    background = Color.White,
    surface = Color.White,
)

@Composable
fun SimpleTaskTodoTheme(
    darkTheme: Boolean = true,
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}