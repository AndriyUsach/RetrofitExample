package com.example.retrofitexample.model

import com.example.retrofitexample.api.StudentApi
import com.example.retrofitexample.common.Constants.BASE_STUDENT_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//object RetrofitInstance {
//
//    val api: StudentApi by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_STUDENT_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(StudentApi::class.java)
//    }
//}