package com.example.retrofitexample.repository

import android.util.Log
import com.example.retrofitexample.api.StudentApi
import com.example.retrofitexample.model.Student
import retrofit2.HttpException
import javax.inject.Inject

class StudentRepository @Inject constructor(private val api: StudentApi) {
    suspend fun getStudentList(callback: (List<Student>?) -> Unit) {
        val a: List<Student> = api.getStudents()
        Log.d("Response1", a.toString())
        callback(a)
    }

    suspend fun getStudentById(id: Int, callback: (List<Student>?) -> Unit) {
        val studentList = mutableListOf<Student>()
        try {
            val student = api.getStudentById(id.toString())
            studentList.add(student)
        } catch (e: HttpException) {
            println(e)
            Log.d("HttpException", e.message())
        }
        Log.d("Response2", studentList.toString())
        callback(studentList)
    }
}