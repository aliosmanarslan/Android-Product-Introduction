package com.aliosmanarslan.aliexpress.models


import com.google.gson.annotations.SerializedName

data class UserLogin(
    @SerializedName("user")
    val user: List<User?>?
)
    data class User(
        @SerializedName("bilgiler")
        val bilgiler: Bilgiler?,
        @SerializedName("durum")
        val durum: Boolean?,
        @SerializedName("mesaj")
        val mesaj: String?
    )
        data class Bilgiler(
            @SerializedName("face")
            val face: String,
            @SerializedName("faceID")
            val faceID: String,
            @SerializedName("userEmail")
            val userEmail: String,
            @SerializedName("userId")
            val userId: String,
            @SerializedName("userName")
            val userName: String,
            @SerializedName("userPhone")
            val userPhone: String,
            @SerializedName("userSurname")
            val userSurname: String
        )

