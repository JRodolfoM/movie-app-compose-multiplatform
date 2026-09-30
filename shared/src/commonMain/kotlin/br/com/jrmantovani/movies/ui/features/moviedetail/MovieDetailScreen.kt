package br.com.jrmantovani.movies.ui.features.moviedetail

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.jrmantovani.movies.domain.Movie
import br.com.jrmantovani.movies.domain.movie1
import br.com.jrmantovani.movies.ui.components.AlertDialogErrorTrailer
import br.com.jrmantovani.movies.ui.components.CastMemberItem
import br.com.jrmantovani.movies.ui.components.ModalWatchTrailer
import br.com.jrmantovani.movies.ui.components.MovieGenreChip
import br.com.jrmantovani.movies.ui.components.MovieInfoItem
import br.com.jrmantovani.movies.ui.theme.MoviesAppTheme
import coil3.Image
import coil3.compose.AsyncImage
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.Calendar
import compose.icons.fontawesomeicons.solid.Clock
import compose.icons.fontawesomeicons.solid.Play
import compose.icons.fontawesomeicons.solid.Star
import movies.shared.generated.resources.Res
import movies.shared.generated.resources.movie_detail_title
import movies.shared.generated.resources.movie_detail_watch_trailer
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieDetailsRoute(
    viewModel: MovieDetailViewModel = koinViewModel(),
    navigateBack: () -> Unit,
) {
    val movieDetailState by viewModel.movieDetailState.collectAsState()

     val trailerState by viewModel.trailerState.collectAsState()
    MovieDetailsScreen(
        movieDetailState = movieDetailState,
        trailerState = trailerState,
        onCheckTrailer = { key ->
            viewModel.isEmbeddable(key)
        },
        onNavigationIconClick = navigateBack
    )
}


@Composable
fun MovieDetailsScreen(
    movieDetailState: MovieDetailViewModel.MovieDetailState,
    trailerState: MovieDetailViewModel.TrailerState,
    onCheckTrailer: (key: String) -> Unit,
    onNavigationIconClick: () -> Unit,

) {
    var youtubeVideoId by remember { mutableStateOf<String?>(null) }
    var showErrorModal by remember { mutableStateOf<String?>(null) }
    var pendingTrailerKey by remember { mutableStateOf<String?>(null) }
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(trailerState) {
        when (trailerState) {
            is MovieDetailViewModel.TrailerState.Success -> {
                if (trailerState.status) {
                    youtubeVideoId = pendingTrailerKey
                } else {
                    showErrorModal = pendingTrailerKey
                }
                pendingTrailerKey = null
            }
            is MovieDetailViewModel.TrailerState.Error -> {
                showErrorModal = pendingTrailerKey
                pendingTrailerKey = null
            }
            else -> {}
        }
    }

    ModalWatchTrailer(
        youtubeKey = youtubeVideoId,
        onDismiss = { youtubeVideoId = null }
    )

    AlertDialogErrorTrailer(
        youtubeKey = showErrorModal,
        onDismiss = { showErrorModal = null },
        onConfirm = { key ->
            showErrorModal = null
            uriHandler.openUri("https://www.youtube.com/watch?v=$key")
        }
    )

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(Res.string.movie_detail_title)) },
                navigationIcon = {
                    Surface (modifier = Modifier
                        .padding(start = 12.dp),
                        shape = MaterialTheme.shapes.small
                    ){
                        IconButton(
                            onClick = {onNavigationIconClick() },
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

      Box(
          modifier = Modifier
              .fillMaxSize()
              .padding(paddingValues),
          contentAlignment = Alignment.Center

      ){


          when(movieDetailState){
              MovieDetailViewModel.MovieDetailState.Loading -> {
                  CircularProgressIndicator()
              }
              is MovieDetailViewModel.MovieDetailState.Success -> {
                  MovieDetailsContent(
                      movie = movieDetailState.movie,
                      trailerLoading = trailerState is MovieDetailViewModel.TrailerState.Loading,
                      onWatchTrailerClick = { key ->
                          pendingTrailerKey = key
                          onCheckTrailer(key)


                      }
                  )

              }
              is MovieDetailViewModel.MovieDetailState.Error -> {
                  Text(
                      text = movieDetailState.message,
                      color = MaterialTheme.colorScheme.error,
                      style = MaterialTheme.typography.bodyMedium,
                  )
              }
          }
      }

    }
}



@Composable
fun MovieDetailsContent(
    modifier: Modifier = Modifier,
    movie: Movie,
    trailerLoading: Boolean,
    onWatchTrailerClick: (key: String) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)

    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .weight(1f),
            shape = MaterialTheme.shapes.large

        ) {
           AsyncImage(
                model = movie.posterPath,
                contentDescription = null,
                modifier = Modifier
                    .clip(
                        MaterialTheme.shapes.large,

                        ),
                contentScale = ContentScale.Crop
            )

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                    text = movie.rating

                )
                Spacer(modifier = Modifier.width(16.dp))
                movie.duration?.let { duration ->
                    MovieInfoItem(
                        icon = FontAwesomeIcons.Solid.Clock,
                        text = duration
                    )

                }

                Spacer(modifier = Modifier.width(16.dp))
                MovieInfoItem(
                    icon = FontAwesomeIcons.Solid.Calendar,
                    text = movie.year.toString()

                )

            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                movie.genres?.forEachIndexed {index, genre ->
                    MovieGenreChip(
                        genre = genre.name
                    )

                    if(index < movie.genres.size - 1){
                        Spacer(modifier = Modifier.width(8.dp))

                    }
                }

            }
            Spacer(modifier = Modifier.height(8.dp))

            movie.movieTrailerYoutubeKey?.let{ key ->
                ElevatedButton(

                    onClick = {
                        if(trailerLoading) return@ElevatedButton

                       onWatchTrailerClick(key)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)


                ) {
                    if(trailerLoading){
                        CircularProgressIndicator(
                            modifier = Modifier.size(12.dp)
                        )


                    }else{
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Play,
                            contentDescription = null,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = stringResource(Res.string.movie_detail_watch_trailer),
                            modifier = Modifier.padding(start = 16.dp),
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.bodyMedium

                        )

                    }

                }
            }

            movie.castMembers?.let{ castMembers ->

                Spacer(modifier = Modifier.height(16.dp))

                BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                    val itemWidth = this.maxWidth * 0.55f

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(castMembers) { castMember->
                            CastMemberItem(
                                profilePictureUrl = castMember.profilePath,
                                name = castMember.name,
                                character = castMember.character,
                                modifier = Modifier.width(itemWidth)
                            )
                        }
                    }


                }
            }


            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
            ) {
                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.bodySmall
                )
            }


        }
    }
}



@Preview(showBackground = true)
@Composable
private fun MovieDetailsScreenPreview() {
    MoviesAppTheme{
        MovieDetailsScreen(
            movieDetailState = MovieDetailViewModel.MovieDetailState.Success(movie1),
            trailerState = MovieDetailViewModel.TrailerState.Success(false),
            onCheckTrailer = {},
            onNavigationIconClick = {}
        )
        }
}