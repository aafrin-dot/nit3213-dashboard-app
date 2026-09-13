package com.example.a8057043assignment2.data.model

import com.squareup.moshi.Json

data class Entity(
    @Json(name = "field") val field: String,
    @Json(name = "concept") val concept: String,
    @Json(name = "scientist") val scientist: String,
    @Json(name = "yearProposed") val yearProposed: Int,
    @Json(name = "branch") val branch: String,
    @Json(name = "description") val description: String
)