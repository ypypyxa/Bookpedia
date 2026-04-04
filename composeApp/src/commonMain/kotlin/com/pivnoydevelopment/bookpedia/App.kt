package com.pivnoydevelopment.bookpedia

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.pivnoydevelopment.bookpedia.book.presentation.book_list.BookListScreenRoot
import com.pivnoydevelopment.bookpedia.book.presentation.book_list.BookListViewModel

@Composable
@Preview
fun App() {
    BookListScreenRoot(
        viewModel = BookListViewModel(),
        onBookClick = {}
    )
}