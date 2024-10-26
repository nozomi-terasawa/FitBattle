package org.example.project.infrastructure.serializationData

import kotlinx.serialization.Serializable

@Serializable
data class PassedRes(
    val success: Boolean,
    val passedMember: List<MemberInfo>,
)
