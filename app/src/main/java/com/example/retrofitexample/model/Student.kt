package com.example.retrofitexample.model

import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("university")
    val campus: String,

    @SerializedName("faculty")
    val faculty: String,

    @SerializedName("group")
    val group: String
)
