package com.pivnoydevelopment.bookpedia

import androidx.compose.runtime.*
import com.pivnoydevelopment.bookpedia.book.data.network.KtorRemoteBookDataSource
import com.pivnoydevelopment.bookpedia.book.data.repository.DefaultBookRepository
import com.pivnoydevelopment.bookpedia.book.presentation.book_list.BookListScreenRoot
import com.pivnoydevelopment.bookpedia.book.presentation.book_list.BookListViewModel
import com.pivnoydevelopment.bookpedia.core.data.HttpClientFactory
import io.ktor.client.engine.HttpClientEngine

@Composable
// @Preview
fun App(engine: HttpClientEngine) {
    BookListScreenRoot(
        viewModel = BookListViewModel(
            bookRepository = DefaultBookRepository(
                remoteBookDataSource = KtorRemoteBookDataSource(
                    httpClient = HttpClientFactory.create(engine)
                )
            )
        ),
        onBookClick = {}
    )
}