package com.example.retrofitexample.api

import com.example.retrofitexample.model.Student
import retrofit2.http.GET
import retrofit2.http.Path

interface StudentApi {

    @GET("students")
    suspend fun getStudents(): List<Student>

    @GET("students/{id}")
    suspend fun getStudentById(@Path("id")id: String): Student
}