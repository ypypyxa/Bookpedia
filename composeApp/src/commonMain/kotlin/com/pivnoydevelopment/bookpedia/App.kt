package com.pivnoydevelopment.bookpedia

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.pivnoydevelopment.bookpedia.book.presentation.book_list.BookListScreen
import com.pivnoydevelopment.bookpedia.book.presentation.book_list.BookListState
import com.pivnoydevelopment.bookpedia.book.presentation.book_list.books

@Composable
@Preview
fun App() {
    BookListScreen(
        state = BookListState(
            searchResults = books
        ),
        onAction = {}
    )
}