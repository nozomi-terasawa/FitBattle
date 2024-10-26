package org.example.project.infrastructure

import kotlinx.serialization.Serializable

@Serializable
data class SaveFitnessReq(
    val userId: Int,
    val calories: Float,
    val timestamp: String,
)
