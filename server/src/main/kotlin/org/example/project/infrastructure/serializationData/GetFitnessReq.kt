package org.example.project.infrastructure.serializationData

import kotlinx.serialization.Serializable

@Serializable
data class GetFitnessReq(
    val userId: Int,
)
