package com.pivnoydevelopment.bookpedia.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object BookGraph: Route

    @Serializable
    data class BookDetail(val id: String): Route

    @Serializable
    data object BookList: Route
}