package com.aliosmanarslan.aliexpress.models


import com.google.gson.annotations.SerializedName

data class UserRegister(
    @SerializedName("user")
    val user: List<URegister?>?
)
    data class URegister(
        @SerializedName("durum")
        val durum: Boolean,
        @SerializedName("userId")
        val userId: String?,
        @SerializedName("mesaj")
        val mesaj: String
    )
