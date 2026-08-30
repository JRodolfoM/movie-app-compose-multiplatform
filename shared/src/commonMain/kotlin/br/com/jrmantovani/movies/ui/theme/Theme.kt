package br.com.jrmantovani.movies.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable

@Composable
fun MoviesAppTheme(
    darkTheme:Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Typography(),
        shapes = Shapes(),
        content = content
    )
}