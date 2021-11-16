package com.arthurfp.cookaholic.models


import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("text")
    val text: String
)