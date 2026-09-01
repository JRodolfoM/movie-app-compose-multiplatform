package br.com.jrmantovani.movies.ui.features.moviedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.jrmantovani.movies.domain.Movie
import br.com.jrmantovani.movies.domain.movie1
import br.com.jrmantovani.movies.ui.theme.MoviesAppTheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft

@Composable
fun MovieDetailsRoute() {
    MovieDetailsScreen(movie = movie1)
}


@Composable
fun MovieDetailsScreen(movie: Movie) {
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Movie Details") },
                navigationIcon = {
                    Surface (modifier = Modifier
                        .padding(start = 12.dp),
                        shape = MaterialTheme.shapes.small
                    ){
                        IconButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .size(32.dp)


                        ){
                            Icon(
                                imageVector = FontAwesomeIcons.Solid.ArrowLeft,
                                contentDescription = null,
                                modifier = Modifier.padding(8.dp)
                            )

                        }


                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,

                )
                )
        }
    ){ paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
        ){

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MovieDetailsScreenPreview() {
    MoviesAppTheme{
        MovieDetailsScreen(movie1)
        }
}