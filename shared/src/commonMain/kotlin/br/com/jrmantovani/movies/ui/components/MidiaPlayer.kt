package br.com.jrmantovani.movies.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun YouTubePlayer(url: String, modifier: Modifier = Modifier)