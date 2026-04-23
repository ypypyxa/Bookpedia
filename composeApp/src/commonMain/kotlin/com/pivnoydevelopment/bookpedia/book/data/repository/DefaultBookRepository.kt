package com.pivnoydevelopment.bookpedia.book.data.repository

import androidx.sqlite.SQLiteException
import com.pivnoydevelopment.bookpedia.book.data.database.FavoriteBookDao
import com.pivnoydevelopment.bookpedia.book.data.mapper.toBook
import com.pivnoydevelopment.bookpedia.book.data.mapper.toBookEntity
import com.pivnoydevelopment.bookpedia.book.data.network.RemoteBookDataSource
import com.pivnoydevelopment.bookpedia.book.domain.model.Book
import com.pivnoydevelopment.bookpedia.book.domain.repository.BookRepository
import com.pivnoydevelopment.bookpedia.core.domain.DataError
import com.pivnoydevelopment.bookpedia.core.domain.EmptyResult
import com.pivnoydevelopment.bookpedia.core.domain.Result
import com.pivnoydevelopment.bookpedia.core.domain.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource,
    private val favoriteBookDao: FavoriteBookDao
): BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource
            .searchBooks(query)
            .map { dto ->
                dto.results.map { it.toBook() }
            }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
        val localResult = favoriteBookDao.getFavoriteBook(bookId)

        return if (localResult == null) {
            remoteBookDataSource
                .getBookDetails(bookId)
                .map { it.description }
        } else {
            Result.Success(localResult.description)
        }
    }

    override fun getFavoritesBooks(): Flow<List<Book>> {
        return favoriteBookDao
            .getFavoritesBooks()
            .map { bookEntities ->
                bookEntities.map { it.toBook() }
            }
    }

    override fun isBookFavorite(id: String): Flow<Boolean> {
        return favoriteBookDao
            .getFavoritesBooks()
            .map { bookEntities ->
                bookEntities.any { it.id == id }
            }
    }

    override suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local> {
        return try {
            favoriteBookDao.upsert(book.toBookEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromFavorites(id: String) {
        favoriteBookDao.deleteFavoriteBook(id)
    }
}