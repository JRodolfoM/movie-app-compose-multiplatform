package br.com.jrmantovani.movies.ui.features.moviedetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.jrmantovani.movies.domain.Movie
import br.com.jrmantovani.movies.domain.movie1
import br.com.jrmantovani.movies.ui.components.CastMemberItem
import br.com.jrmantovani.movies.ui.components.MovieGenreChip
import br.com.jrmantovani.movies.ui.components.MovieInfoItem
import br.com.jrmantovani.movies.ui.theme.MoviesAppTheme
import coil3.Image
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.Calendar
import compose.icons.fontawesomeicons.solid.Clock
import compose.icons.fontawesomeicons.solid.Play
import compose.icons.fontawesomeicons.solid.Star
import movies.shared.generated.resources.Res
import movies.shared.generated.resources.homem_araranha_movie
import org.jetbrains.compose.resources.painterResource

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

            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .weight(1f),
                shape= MaterialTheme.shapes.large

            ){
                Image(
                    painter = painterResource(Res.drawable.homem_araranha_movie),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(
                            MaterialTheme.shapes.large,

                        ),
                    contentScale = ContentScale.Crop
                )

            }

            Column (modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = movie.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MovieInfoItem(
                        icon = FontAwesomeIcons.Solid.Star,
                        text = "7.5"

                    )
                    Spacer(modifier = Modifier.width(16.dp))

                    MovieInfoItem(
                        icon = FontAwesomeIcons.Solid.Clock,
                        text = "2h 36 min"

                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    MovieInfoItem(
                        icon = FontAwesomeIcons.Solid.Calendar,
                        text = "2026"

                    )

                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MovieGenreChip(
                        genre = "Action"
                    )

                }
                Spacer(modifier = Modifier.height(8.dp))
                ElevatedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)


                ){
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.Play,
                        contentDescription = null,
                        modifier = Modifier.size(12.dp)
                        )
                    Text(
                        text = "Watch Trailer",
                        modifier = Modifier.padding(start = 16.dp),
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.bodyMedium

                    )

                }
                Spacer(modifier = Modifier.height(16.dp))

                BoxWithConstraints (modifier = Modifier.fillMaxWidth()){
                    val itemWidth = this.maxWidth * 0.55f

                    LazyRow (
                        contentPadding = PaddingValues( horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        items(10){
                            CastMemberItem(
                                profilePictureUrl = "",
                                name = "Tom Holland",
                                character = "Peter Parker",
                                modifier = Modifier.width(itemWidth)
                            )
                        }
                    }



                }


                Box(modifier = Modifier.padding(16.dp)){
                    Text(
                        text = "omem-Aranha: Um Novo Dia explora a vida de Peter Parker após os acontecimentos de Homem-Aranha: Sem Volta Para Casa. Vivendo em Nova York depois que sua identidade foi apagada da memória de todos, o amigão da vizinhança tenta seguir em frente anonimamente enquanto concilia a rotina universitária com a missão de proteger a cidade como super-herói. Sem poder contar com seus amigos e obrigado a enfrentar tudo sozinho, Peter busca se reencontrar ao mesmo tempo em que se sente invisível e vê sua namorada vivendo novas experiências. Porém, uma série de acontecimentos coloca a cidade em perigo novamente, fazendo com que ele enfrente vilões cada vez mais poderosos e tome decisões que colocarão à prova seus valores e o verdadeiro significado do sacrifício, mostrando que, de fato, com grandes poderes vêm grandes responsabilidades. Em uma jornada marcada por recomeços, Peter precisará descobrir se é possível adaptar-se à nova realidade sem abrir mão daquilo que faz dele o Homem-Aranha.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }




            }

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