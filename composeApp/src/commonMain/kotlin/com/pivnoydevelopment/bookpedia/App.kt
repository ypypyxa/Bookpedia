package com.pivnoydevelopment.bookpedia

import androidx.compose.runtime.*
import com.pivnoydevelopment.bookpedia.book.presentation.book_list.BookListScreenRoot
import com.pivnoydevelopment.bookpedia.book.presentation.book_list.BookListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    val viewModel = koinViewModel<BookListViewModel>()
    BookListScreenRoot(
        viewModel = viewModel,
        onBookClick = {}
    )
}