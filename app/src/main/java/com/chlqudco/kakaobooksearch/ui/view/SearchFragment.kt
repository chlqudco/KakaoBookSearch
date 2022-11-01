package com.chlqudco.kakaobooksearch.ui.view

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chlqudco.kakaobooksearch.databinding.FragmentSearchBinding
import com.chlqudco.kakaobooksearch.ui.adapter.BookSearchAdapter
import com.chlqudco.kakaobooksearch.ui.viewmodel.BookSearchViewModel
import com.chlqudco.kakaobooksearch.util.Constants.SEARCH_BOOKS_TIME_DELAY

class SearchFragment: Fragment(){
    //뷰바인딩 설정,
    private var _binding : FragmentSearchBinding? = null
    private val binding get() = _binding!!

    //뷰모델
    private lateinit var bookSearchViewModel: BookSearchViewModel

    //어댑터
    private lateinit var bookSearchAdapter: BookSearchAdapter

    //바인딩 초기화
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    //뷰모델 초기화
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookSearchViewModel = (activity as MainActivity).bookSearchViewModel

        setupRecyclerView()
        searchBooks()

        //뷰모델의 라이브데이터를 프래그먼트에서 감시하자
        bookSearchViewModel.searchResult.observe(viewLifecycleOwner) { response ->
            val books = response.documents
            bookSearchAdapter.submitList(books)
        }
    }

    private fun setupRecyclerView(){
        bookSearchAdapter = BookSearchAdapter()
        binding.rvSearchResult.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            //아이템 사이에 줄 그어줌
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = bookSearchAdapter
        }

        //어댑터에 클릭리스너 달아주기
        bookSearchAdapter.setOnItemClickListener {
            // 북프래그먼트로 이동해라!
            val action = SearchFragmentDirections.actionFragmentSearchToFragmentBook(it)
            findNavController().navigate(action)
        }
    }

    private fun searchBooks(){
        //사용자의 입력 시간 간격 조절을 위해
        var startTime = System.currentTimeMillis()
        var endTime: Long

        //쿼리 불러오기
        binding.etSearch.text = Editable.Factory.getInstance().newEditable(bookSearchViewModel.query)

        //알아서 갖고 오기
        binding.etSearch.addTextChangedListener { text: Editable? ->
            endTime = System.currentTimeMillis()
            if (endTime - startTime >= SEARCH_BOOKS_TIME_DELAY){
                text?.let {
                    //공백 자르기
                    val query = it.toString().trim()
                    if (query.isNotEmpty()){
                        //값 가져오기
                        bookSearchViewModel.searchBooks(query)
                        //쿼리 저장하기
                        bookSearchViewModel.query = query
                    }
                }
            }
            startTime = endTime
        }
    }

    //바인딩 해제를 위함
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}