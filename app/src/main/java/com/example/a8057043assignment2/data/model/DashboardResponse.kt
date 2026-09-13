package com.example.a8057043assignment2.data.model

import com.squareup.moshi.Json

data class DashboardResponse(
    @Json(name = "entities") val entities: List<Entity>,
    @Json(name = "entityTotal") val entityTotal: Int
)