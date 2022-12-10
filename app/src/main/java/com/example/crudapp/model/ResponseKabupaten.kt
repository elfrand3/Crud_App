package com.example.crudapp.model

import com.google.gson.annotations.SerializedName

data class ResponseKabupaten(
    @SerializedName("kota_kabupaten") val kota_kabupaten : List<DataKabupaten>
)

data class DataKabupaten(
    @SerializedName("id") val id: Int,
    @SerializedName("id_provinsi") val id_provinsi : Int,
    @SerializedName("nama") val nama : String,
)
