package br.com.jrmantovani.movies.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import br.com.jrmantovani.movies.ui.theme.MoviesAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalWatchTrailer(
    youtubeKey: String? = null,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {

    if (youtubeKey != null) {
        ModalBottomSheet(
            onDismissRequest = { onDismiss() },
            modifier = modifier,
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            containerColor = Color.Black
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {

                YouTubePlayer(
                    url ="https://www.youtube.com/embed/$youtubeKey",
                    modifier = Modifier.fillMaxSize()
                )


            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun ModalWatchTrailerPreview() {
    MoviesAppTheme {
        ModalWatchTrailer(
            youtubeKey = "1234",
            onDismiss = {}
        )
    }
}
