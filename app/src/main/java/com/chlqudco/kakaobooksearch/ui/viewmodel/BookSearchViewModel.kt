package com.chlqudco.kakaobooksearch.ui.viewmodel

import androidx.lifecycle.*
import com.chlqudco.kakaobooksearch.data.model.SearchResponse
import com.chlqudco.kakaobooksearch.data.repository.BookSearchRepository
import kotlinx.coroutines.launch

class BookSearchViewModel(
    private val bookSearchRepository: BookSearchRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    //책 검색 결과 Livedata로 다루기
    private val _searchResult = MutableLiveData<SearchResponse>()
    val searchResult: LiveData<SearchResponse> get() = _searchResult

    //책 검색 결과 가져오는 함수
    fun searchBooks(query: String) = viewModelScope.launch{
        val response = bookSearchRepository.searchBooks(query, "accuracy", 1, 15)
        if (response.isSuccessful){
            response.body()?.let { body ->
                _searchResult.postValue(body)
            }
        }
    }

    //SavedState
    var query = String()
        set(value) {
            field = value
            savedStateHandle.set(SAVE_STATE_KEY, value)
        }

    init {
        query = savedStateHandle.get<String>(SAVE_STATE_KEY) ?: ""
    }

    companion object{
        private const val SAVE_STATE_KEY = "query"
    }
}

