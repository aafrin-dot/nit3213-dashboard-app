package com.example.a8057043assignment2.data.model

import com.squareup.moshi.Json

data class LoginResponse(
    @Json(name = "keypass") val keypass: String
)