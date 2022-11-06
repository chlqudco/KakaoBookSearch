package com.chlqudco.kakaobooksearch.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.chlqudco.kakaobooksearch.databinding.FragmentBookBinding
import com.chlqudco.kakaobooksearch.ui.viewmodel.BookViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookFragment : Fragment(){
    private var _binding: FragmentBookBinding? = null
    private val binding get() = _binding!!

    //받아올 인자값들
    private val args by navArgs<BookFragmentArgs>()

    //뷰머델
    //힐트 안쓴 버전
    //private lateinit var bookSearchViewModel: BookSearchViewModel
    //힐트 쓴버전
    //private val bookSearchViewModel by activityViewModels<BookSearchViewModel>()
    private val bookViewModel by viewModels<BookViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 뷰모델 초기화
        //힐트 쓴 이상 초기화 할 필요가 없어짐
       //bookSearchViewModel = (activity as MainActivity).bookSearchViewModel

        //책 받아오고
        val book = args.book
        //웹뷰에 적용하기
        binding.webview.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(book.url)
        }

        //fab 클릭 리스너
        binding.fabFavor.setOnClickListener {
            bookViewModel.saveBook(book)
            Snackbar.make(view, "Book has saved", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onPause() {
        binding.webview.onPause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        binding.webview.onResume()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}