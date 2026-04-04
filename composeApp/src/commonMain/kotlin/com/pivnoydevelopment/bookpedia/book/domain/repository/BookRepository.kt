package com.pivnoydevelopment.bookpedia.book.domain.repository

import com.pivnoydevelopment.bookpedia.book.domain.model.Book
import com.pivnoydevelopment.bookpedia.core.domain.DataError
import com.pivnoydevelopment.bookpedia.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
}