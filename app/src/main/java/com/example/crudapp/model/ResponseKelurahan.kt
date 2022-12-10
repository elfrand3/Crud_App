package com.example.crudapp.model

import com.google.gson.annotations.SerializedName

data class ResponseKelurahan(
    @SerializedName("kelurahan") val kelurahan : List<DataKelurahan>
)

data class DataKelurahan(
    @SerializedName("id") val id: Int,
    @SerializedName("id_kecamatan") val id_kecamatan : Int,
    @SerializedName("nama") val nama : String,
)
