package com.example.crudapp.networking

import com.example.crudapp.model.ResponseSave
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiEndpoint {

    @POST("/api/users")
    @FormUrlEncoded
    fun saveData(
        @Field("name") name: String,
        @Field("job") job: String,
    ):Call <ResponseSave>

}