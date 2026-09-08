package br.com.jrmantovani.movies.utils

import java.util.Locale

actual fun Double.formatRating(): String =
    String.format(Locale.getDefault(), "%.1f", this)