package com.example.scratch.coroutines.fragment.retrofit.data

import com.example.scratch.BuildConfig
import com.example.scratch.coroutines.fragment.retrofit.utils.Constants.Companion.BASE_URL
import com.example.scratch.coroutines.fragment.retrofit.utils.Constants.Companion.SAMPLE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private val retrofit by lazy {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .connectTimeout(timeout = 30L, TimeUnit.SECONDS)
            .readTimeout(timeout = 30L, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            client.addInterceptor(interceptor)
        }
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
    }
    val api:SampleApi by lazy {
        retrofit.create(SampleApi::class.java)
    }
}