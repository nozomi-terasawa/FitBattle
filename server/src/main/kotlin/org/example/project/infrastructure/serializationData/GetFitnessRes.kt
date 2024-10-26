package org.example.project.infrastructure.serializationData

import kotlinx.serialization.Serializable

@Serializable
data class GetFitnessRes(
    val success: Boolean,
    val fitness: SaveFitnessReq?,
)
