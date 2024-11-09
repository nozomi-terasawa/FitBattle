package org.example.project.domain.entities.location

data class GeoFenceInfo(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val radius: Float,
)
