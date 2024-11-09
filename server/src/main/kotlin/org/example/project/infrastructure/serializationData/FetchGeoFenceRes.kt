package org.example.project.infrastructure.serializationData

import kotlinx.serialization.Serializable

@Serializable
data class FetchGeoFenceRes(
    val success: Boolean,
    val geoFence: List<GeoFenceInfoRes>,
)
