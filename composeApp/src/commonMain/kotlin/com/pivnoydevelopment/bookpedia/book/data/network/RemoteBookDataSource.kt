package com.pivnoydevelopment.bookpedia.book.data.network

import com.pivnoydevelopment.bookpedia.book.data.dto.SearchResponseDto
import com.pivnoydevelopment.bookpedia.core.domain.DataError
import com.pivnoydevelopment.bookpedia.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.Remote>
}