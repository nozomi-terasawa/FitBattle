package org.example.project.infrastructure.serializationData

import kotlinx.serialization.Serializable

@Serializable
data class UserCreateRes(
    val userId: Int,
    val token: String,
)
