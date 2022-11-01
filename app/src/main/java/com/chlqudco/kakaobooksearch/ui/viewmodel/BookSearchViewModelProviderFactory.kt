package com.chlqudco.kakaobooksearch.ui.viewmodel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.chlqudco.kakaobooksearch.data.repository.BookSearchRepository

//뷰모델 초기값으로 레포를 전달받아야 하는데 뷰모델은 초기값을 전달받을 수 없으므로 팩토리를 만들어야 함
/*
//SavedStateHandle 미적용 버전
@Suppress("UNCHECKED_CAST")
class BookSearchViewModelProviderFactory(
    private val bookSearchRepository: BookSearchRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookSearchViewModel::class.java)){
            return BookSearchViewModel(bookSearchRepository) as T
        }
        throw IllegalArgumentException("ViewModel class not found")
    }
}
*/

@Suppress("UNCHECKED_CAST")
class BookSearchViewModelProviderFactory(
    private val bookSearchRepository: BookSearchRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {

        if (modelClass.isAssignableFrom(BookSearchViewModel::class.java)){
            return BookSearchViewModel(bookSearchRepository, handle) as T
        }

        throw IllegalArgumentException("ViewModel class not found")
    }
}