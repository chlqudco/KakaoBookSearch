package com.chlqudco.kakaobooksearch.data.api

import com.chlqudco.kakaobooksearch.util.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

//하나의 오브젝트만 만들어지도록 싱글턴으로 구현
object RetrofitInstance {

    //이걸 레트로핏에 등록하면 로그캣에서 응답을 확인할 수 있음
    private val okHttpClient: OkHttpClient by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    //말그대로 레트로핏 객체
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }

    //책 검색 통신에 바로 사용 가능한 변수
    val api: BookSearchApi by lazy {
        retrofit.create(BookSearchApi::class.java)
    }
}