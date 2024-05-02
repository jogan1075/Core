package com.jmc.core.network.models

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

    @SerializedName("message")
    val message: String?,
    @SerializedName("status_code")
    val status: Int?,
    @SerializedName("uri")
    val url : String)