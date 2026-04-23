package com.pivnoydevelopment.bookpedia.book.data.mapper

import com.pivnoydevelopment.bookpedia.book.data.database.BookEntity
import com.pivnoydevelopment.bookpedia.book.data.dto.SearchedBookDto
import com.pivnoydevelopment.bookpedia.book.domain.model.Book

fun SearchedBookDto.toBook(): Book {
    return Book(
        id = id.substringAfterLast("/"),
        title = title,
        imageUrl = if (coverKey != null) {
            "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
        } else {
            "https://covers.openlibrary.org/b/olid/${coverAlternativeKey}-L.jpg"
        },
        authors = authorNames ?: emptyList(),
        description = null,
        languages = languages ?: emptyList(),
        firstPublishedYear = firstPublishYear.toString(),
        averageRating = ratingsAverage,
        ratingCount = ratingsCount,
        numPages = numPagesMedian,
        numEditions = numEditions ?: 0
    )
}

fun Book.toBookEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        languages = languages,
        authors = authors,
        firstPublishYear = firstPublishedYear,
        ratingsCount = ratingCount,
        ratingsAverage = averageRating,
        numPagesMedian = numPages,
        numEditions = numEditions
    )
}
fun BookEntity.toBook(): Book {
    return Book(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        languages = languages,
        authors = authors,
        firstPublishedYear = firstPublishYear,
        ratingCount = ratingsCount,
        averageRating = ratingsAverage,
        numPages = numPagesMedian,
        numEditions = numEditions
    )
}