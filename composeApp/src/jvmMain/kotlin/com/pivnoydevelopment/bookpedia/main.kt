package com.pivnoydevelopment.bookpedia

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.pivnoydevelopment.bookpedia.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Bookpedia",
        ) {
            App()
        }
    }
}