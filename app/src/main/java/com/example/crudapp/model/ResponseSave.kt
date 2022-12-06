package com.example.crudapp.model

import com.google.gson.annotations.SerializedName

data class ResponseSave(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("job")
	val job: String
)
