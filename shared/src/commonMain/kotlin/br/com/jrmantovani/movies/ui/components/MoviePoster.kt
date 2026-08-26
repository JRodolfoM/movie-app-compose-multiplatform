package br.com.jrmantovani.movies.ui.components

import androidx.compose.foundation.Image
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
import movies.shared.generated.resources.Res
import movies.shared.generated.resources.homem_araranha_movie
import org.jetbrains.compose.resources.painterResource

@Composable
fun MoviePoster(modifier: Modifier = Modifier) {
    Column (modifier = modifier
        .width(140.dp)){
        Card (modifier = Modifier
            .width(140.dp)
            .height(210.dp),
            shape = RoundedCornerShape(12.dp)
        ){
            Image(
                painter = painterResource(Res.drawable.homem_araranha_movie),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        Text(text = "Homem-Aranha: Um Novo Dia",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium
            )
    }
}





@Preview(showBackground = true)
@Composable
private fun MoviePosterPreview() {
    MaterialTheme{
        MoviePoster()
       }
}