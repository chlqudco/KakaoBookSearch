package com.chlqudco.kakaobooksearch.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chlqudco.kakaobooksearch.databinding.FragmentSettingsBinding

class SettingsFragment: Fragment(){
    //뷰바인딩 설정,
    private var _binding : FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    //바인딩 초기화
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    //바인딩 해제를 위함
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}