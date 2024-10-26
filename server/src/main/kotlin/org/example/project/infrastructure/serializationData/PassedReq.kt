package org.example.project.infrastructure.serializationData

import kotlinx.serialization.Serializable

@Serializable
data class PassedReq(
    val userId: Int,
    val timestamp: String,
)
