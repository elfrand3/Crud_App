package com.example.crudapp.networking

import com.example.crudapp.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiUser {
    @GET("/api/users?page=2")
    fun getData(): Call<ResponseGet>

    @GET("provinsi")
    fun getProvinsi(): Call<ResponseProvinsi>

    @GET("kota")
    fun getKb(
        @Query("id_provinsi") id_provinsi: String
    ): Call<ResponseKabupaten>

    @GET("kecamatan")
    fun getKec(
        @Query("id_kota") id_kota: String
    ): Call<ResponseKecamatan>

    @GET("kelurahan")
    fun getKel(
        @Query("id_kecamatan") id_kecamatan: String
    ): Call<ResponseKelurahan>
}