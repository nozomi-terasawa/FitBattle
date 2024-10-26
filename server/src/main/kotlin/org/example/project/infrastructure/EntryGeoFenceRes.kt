package org.example.project.infrastructure

import kotlinx.serialization.Serializable

@Serializable
data class EntryGeoFenceRes(
    val success: Boolean,
    val geoFenceEntryLogId: Int,
    val token: String,
    val passingMember: List<MemberInfo>,
)
