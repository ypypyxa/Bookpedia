package com.pivnoydevelopment.bookpedia.book.data.repository

import com.pivnoydevelopment.bookpedia.book.data.mapper.toBook
import com.pivnoydevelopment.bookpedia.book.data.network.RemoteBookDataSource
import com.pivnoydevelopment.bookpedia.book.domain.model.Book
import com.pivnoydevelopment.bookpedia.book.domain.repository.BookRepository
import com.pivnoydevelopment.bookpedia.core.domain.DataError
import com.pivnoydevelopment.bookpedia.core.domain.Result
import com.pivnoydevelopment.bookpedia.core.domain.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource
): BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource
            .searchBooks(query)
            .map { dto ->
                dto.results.map { it.toBook() }
            }
    }
}