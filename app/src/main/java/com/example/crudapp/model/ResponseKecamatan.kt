package com.example.crudapp.model

import com.google.gson.annotations.SerializedName

data class ResponseKecamatan(
    @SerializedName("kecamatan") val kecamatan : List<DataKecamatan>
)

data class DataKecamatan(
    @SerializedName("id") val id: Int,
    @SerializedName("id_kota") val id_kota : Int,
    @SerializedName("nama") val nama : String,
)
