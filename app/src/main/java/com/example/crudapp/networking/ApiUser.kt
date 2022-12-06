package com.example.crudapp.networking

import com.example.crudapp.model.ResponseGet
import retrofit2.Call
import retrofit2.http.GET

interface ApiUser {
    @GET("/api/users?page=2")
    fun getData(): Call<ResponseGet>
}