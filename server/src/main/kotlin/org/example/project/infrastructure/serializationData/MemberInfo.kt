package org.example.project.infrastructure.serializationData

import kotlinx.serialization.Serializable

@Serializable
data class MemberInfo(
    val id: Int,
    val name: String,
    val iconUrl: String,
)
