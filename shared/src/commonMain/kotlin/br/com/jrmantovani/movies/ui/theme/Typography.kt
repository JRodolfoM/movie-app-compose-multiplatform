package br.com.jrmantovani.movies.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle


import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import movies.shared.generated.resources.Res
import movies.shared.generated.resources.urbanist_bold
import movies.shared.generated.resources.urbanist_medium
import movies.shared.generated.resources.urbanist_regular
import org.jetbrains.compose.resources.Font

val urbanist: FontFamily
    @Composable get() = FontFamily(
        Font(Res.font.urbanist_regular, FontWeight.Normal),
        Font(Res.font.urbanist_medium, FontWeight.Medium),
        Font(Res.font.urbanist_bold, FontWeight.Bold),
)

@Composable
fun AppTypography()  = Typography(
   displaySmall = TextStyle(
       fontSize = 26.sp,
       fontFamily = urbanist,
       fontWeight = FontWeight.Normal
   ),

    headlineLarge = TextStyle(
        fontSize = 24.sp,
        fontFamily = urbanist,
        fontWeight = FontWeight.Bold
    ),

    titleLarge = TextStyle(
        fontSize = 20.sp,
        fontFamily = urbanist,
        fontWeight = FontWeight.Normal
    ),

    titleMedium =  TextStyle(
        fontSize = 16.sp,
        fontFamily = urbanist,
        fontWeight = FontWeight.Medium
    ),
    titleSmall =  TextStyle(
        fontSize = 14.sp,
        fontFamily = urbanist,
        fontWeight = FontWeight.Medium
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontFamily = urbanist,
        fontWeight = FontWeight.Normal
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = urbanist,
        fontWeight = FontWeight.Normal
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = urbanist,
        fontWeight = FontWeight.Normal
    ),


    )