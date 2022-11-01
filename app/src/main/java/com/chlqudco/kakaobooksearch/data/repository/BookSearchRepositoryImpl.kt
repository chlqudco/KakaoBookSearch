package com.chlqudco.kakaobooksearch.data.repository

import com.chlqudco.kakaobooksearch.data.api.RetrofitInstance.api
import com.chlqudco.kakaobooksearch.data.model.SearchResponse
import retrofit2.Response

class BookSearchRepositoryImpl: BookSearchRepository {

    override suspend fun searchBooks(query: String, sort: String, page: Int, size: Int): Response<SearchResponse> {
        return api.searchBooks(query, sort, page, size)
    }

}