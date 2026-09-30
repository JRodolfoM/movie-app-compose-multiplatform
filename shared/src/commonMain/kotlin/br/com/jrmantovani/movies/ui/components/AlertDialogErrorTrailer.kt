package br.com.jrmantovani.movies.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.jrmantovani.movies.ui.theme.MoviesAppTheme
import movies.shared.generated.resources.Res
import movies.shared.generated.resources.error_trailer_cancel
import movies.shared.generated.resources.error_trailer_confirm
import movies.shared.generated.resources.error_trailer_message
import movies.shared.generated.resources.error_trailer_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun AlertDialogErrorTrailer(
    youtubeKey: String? = null,
    onDismiss: () -> Unit,
    onConfirm: (key: String) -> Unit
) {
    if (youtubeKey != null) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    text = stringResource(Res.string.error_trailer_title),
                    style = MaterialTheme.typography.titleLarge
                )
            },
            text = {
                Text(
                    text = stringResource(Res.string.error_trailer_message),
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                TextButton(
                    onClick = { onConfirm(youtubeKey) }
                ) {
                    Text(text = stringResource(Res.string.error_trailer_confirm))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismiss
                ) {
                    Text(text = stringResource(Res.string.error_trailer_cancel))
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AlertDialogErrorTrailerPreview() {
    MoviesAppTheme {
        AlertDialogErrorTrailer(
            youtubeKey = "1234",
            onDismiss = {},
            onConfirm = {}
        )
    }
}
