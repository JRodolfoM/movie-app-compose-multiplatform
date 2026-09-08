package br.com.jrmantovani.movies.utils

import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter

actual fun Double.formatRating(): String {
    val formatter = NSNumberFormatter()

    formatter.minimumFractionDigits = 1u
    formatter.maximumIntegerDigits = 1u
    formatter.numberStyle = 1u

    return formatter.stringFromNumber(NSNumber(this)) ?: ""

}