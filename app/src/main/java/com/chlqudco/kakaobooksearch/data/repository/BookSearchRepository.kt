package com.chlqudco.kakaobooksearch.data.repository

import com.chlqudco.kakaobooksearch.data.model.SearchResponse
import retrofit2.Response

interface BookSearchRepository {

    suspend fun searchBooks(query: String, sort: String, page: Int, size: Int) : Response<SearchResponse>
}