package com.aliosmanarslan.aliexpress.models


import com.google.gson.annotations.SerializedName

data class OrderData(
    @SerializedName("order")
    val order: List<Order?>?
)
    data class Order(
        @SerializedName("durum")
        val durum: Boolean?,
        @SerializedName("mesaj")
        val mesaj: String?
    )
