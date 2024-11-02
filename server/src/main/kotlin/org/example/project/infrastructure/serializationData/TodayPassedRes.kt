package org.example.project.infrastructure.serializationData

import kotlinx.serialization.Serializable

@Serializable
data class TodayPassedRes(
    val success: Boolean,
    val passedUser: List<MemberInfo>,
)
