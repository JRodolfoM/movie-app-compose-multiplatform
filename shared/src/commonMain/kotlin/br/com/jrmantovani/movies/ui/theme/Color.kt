package br.com.jrmantovani.movies.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

val Primary80 = Color(0xFFE50914)
val Backgrounddark = Color(0xFF121212)
val Surfacedark = Color(0xFF1E1E1E)
val TextPrimaryDark=Color(0xFFFFFFFF)
val ColorError = Color(0xFFF24E1E)
val Neutral =Color(0xFF8A91A8)

internal val AppColorScheme = darkColorScheme(
    primary = Primary80,
    onPrimary = Color.White,
    background = Backgrounddark,
    onBackground = TextPrimaryDark,
    surface = Surfacedark,
    onSurface = TextPrimaryDark,
    secondary = Neutral,
    onSecondary = TextPrimaryDark,
    error = ColorError,
    onError = Color.White
)