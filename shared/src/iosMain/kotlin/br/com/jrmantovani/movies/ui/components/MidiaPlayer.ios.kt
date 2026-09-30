package br.com.jrmantovani.movies.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.WebKit.WKWebView

@Composable
actual fun YouTubePlayer(url: String, modifier: Modifier) {
    UIKitView(
        modifier = modifier,
        factory = {
            val webView = WKWebView()
            val url = NSURL(string = url)
            webView.loadRequest(NSURLRequest(uRL = url))
            webView
        }
    )
}