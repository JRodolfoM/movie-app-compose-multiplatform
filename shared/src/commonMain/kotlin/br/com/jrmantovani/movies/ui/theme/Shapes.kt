package br.com.jrmantovani.movies.ui.theme

import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val SmallSpacing = 4.dp
val MediumSpacing = 8.dp
val LargeSpacing = 16.dp

val AppShapes = Shapes(
    small = androidx.compose.foundation.shape.RoundedCornerShape(SmallSpacing),
    medium = androidx.compose.foundation.shape.RoundedCornerShape(MediumSpacing),
    large = androidx.compose.foundation.shape.RoundedCornerShape(LargeSpacing)

)