package com.pivnoydevelopment.bookpedia

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform