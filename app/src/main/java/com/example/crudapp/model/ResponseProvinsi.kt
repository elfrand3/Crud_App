package com.example.crudapp.model

import com.google.gson.annotations.SerializedName

data class ResponseProvinsi(
    @SerializedName("provinsi") val provinsi : List<DataProvinsi>
)

data class DataProvinsi(
    @SerializedName("id") val id: Int,
    @SerializedName("nama") val nama: String,
)
