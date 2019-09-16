package com.ahsanzahid.doordashlite.model

import com.google.gson.annotations.SerializedName

data class Restaurant(
    val id: String,
    val name: String,
    val description: String,
    @SerializedName("cover_img_url")
    val coverImageUrl: String,
    val status: String,
    @SerializedName("delivery_fee")
    val deliveryFee: Int
)
