package org.example.project.infrastructure.serializationData

import kotlinx.serialization.Serializable

@Serializable
data class GeoFenceInfoRes(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val radius: Float,
)
