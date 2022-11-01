package com.chlqudco.kakaobooksearch.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.chlqudco.kakaobooksearch.R
import com.chlqudco.kakaobooksearch.data.repository.BookSearchRepositoryImpl
import com.chlqudco.kakaobooksearch.databinding.ActivityMainBinding
import com.chlqudco.kakaobooksearch.ui.viewmodel.BookSearchViewModel
import com.chlqudco.kakaobooksearch.ui.viewmodel.BookSearchViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    //뷰바인딩
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    //뷰모델
    lateinit var bookSearchViewModel: BookSearchViewModel

    //내비게이션 컨트롤러
    private lateinit var navController: NavController

    //앱바
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //다크모드 금지
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

/*
        //앱이 처음 실행 된 경우 서치프래그먼트 실행시키기
        //savedInstanceState로 알 수 있음
        if (savedInstanceState == null){
            binding.bottomNavigationView.selectedItemId = R.id.fragment_search
        }

 */

        setupJetpackNavigation()

        //뷰모델 초기화 하기
        val bookSearchRepository = BookSearchRepositoryImpl()
        val factory = BookSearchViewModelProviderFactory(bookSearchRepository, this)
        bookSearchViewModel = ViewModelProvider(this, factory)[BookSearchViewModel::class.java]
    }

    //바텀내비와 젯팩내비 연동하기
    private fun setupJetpackNavigation(){
        //내비 연결
        val host = supportFragmentManager
            .findFragmentById(R.id.booksearch_nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        //앱바 연결
        //모든프래그먼트를 탑레벨로 설정
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragment_favorite, R.id.fragment_search, R.id.fragment_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    //앱바 셋업을 도와주는 친구
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /*
    //바텀 내비게이션 클릭에 따른 프래그먼트 변화 함수
    private fun setupBottomNavigationView(){
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.fragment_search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, SearchFragment())
                        .commit()
                    true
                }
                R.id.fragment_favorite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, FavoriteFragment())
                        .commit()
                    true
                }
                R.id.fragment_settings -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, SettingsFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
     */
}