package com.chlqudco.kakaobooksearch.data.db

import androidx.paging.PagingSource
import androidx.room.*
import com.chlqudco.kakaobooksearch.data.model.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)

    /*
    라이브데이터 응답
    @Query("SELECT * From books")
    fun getFavoriteBooks(): LiveData<List<Book>>
    */

    //FLOW 응답
    @Query("SELECT * From books")
    fun getFavoriteBooks(): Flow<List<Book>>

    //페이징 처리
    @Query("SELECT * FROM books")
    fun getFavoritePagingBooks(): PagingSource<Int, Book>
}