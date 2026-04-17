package com.pivnoydevelopment.bookpedia.book.presentation.book_detail

import com.pivnoydevelopment.bookpedia.book.domain.model.Book

data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book: Book? = null
)