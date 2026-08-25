package br.com.jrmantovani.movies

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform