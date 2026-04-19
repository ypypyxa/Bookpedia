package com.pivnoydevelopment.bookpedia

import androidx.compose.ui.window.ComposeUIViewController
import com.pivnoydevelopment.bookpedia.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}