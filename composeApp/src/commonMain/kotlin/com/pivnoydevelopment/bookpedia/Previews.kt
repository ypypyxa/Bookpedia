package com.pivnoydevelopment.bookpedia

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.pivnoydevelopment.bookpedia.book.domain.model.Book
import com.pivnoydevelopment.bookpedia.book.presentation.book_list.BookListScreen
import com.pivnoydevelopment.bookpedia.book.presentation.book_list.BookListState
import com.pivnoydevelopment.bookpedia.book.presentation.book_list.components.BookSearchBar

@Preview
@Composable
private fun BookSearchBarPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        BookSearchBar(
            searchQuery = "",
            onSearchQueryChanges = {},
            onImeSearch = {},
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun BookListScreenPreview() {
    BookListScreen(
        state = BookListState(
            isLoading = false,
            searchResults = books
        ),
        onAction = {}
    )
}

val books = (1 .. 100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        imageUrl = "https://test.com",
        authors = listOf("Червяк Уруруха"),
        description = "Описание $it",
        languages = emptyList(),
        firstPublishedYear = null,
        averageRating = 4.67854,
        ratingCount = 5,
        numPages = 100,
        numEditions = 3
    )
}