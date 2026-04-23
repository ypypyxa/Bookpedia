package com.pivnoydevelopment.bookpedia.book.domain.repository

import com.pivnoydevelopment.bookpedia.book.domain.model.Book
import com.pivnoydevelopment.bookpedia.core.domain.DataError
import com.pivnoydevelopment.bookpedia.core.domain.EmptyResult
import com.pivnoydevelopment.bookpedia.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
    suspend fun getBookDescription(bookId: String): Result<String?, DataError>

    fun getFavoritesBooks(): Flow<List<Book>>
    fun isBookFavorite(id: String): Flow<Boolean>
    suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local>
    suspend fun deleteFromFavorites(id: String)
}