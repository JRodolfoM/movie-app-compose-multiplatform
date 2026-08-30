package br.com.jrmantovani.movies.ui.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.jrmantovani.movies.domain.Movie
import br.com.jrmantovani.movies.domain.movie1
import br.com.jrmantovani.movies.ui.theme.MoviesAppTheme
import coil3.compose.AsyncImage

@Composable
fun MoviePoster(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Column (modifier = modifier
        .width(140.dp)){
        Card (modifier = Modifier
            .width(140.dp)
            .height(210.dp),
            shape = RoundedCornerShape(12.dp)
        ){
            AsyncImage(
                model = movie.posterPath,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        Text(text = movie.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium
            )
    }
}





@Preview(showBackground = true)
@Composable
private fun MoviePosterPreview() {
    MoviesAppTheme(){
        MoviePoster(movie1)
       }
}