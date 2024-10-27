package org.example.project.domain.entities.location

import kotlinx.datetime.LocalDateTime

/**
 * ExitGeoFence entity
 * @property [geoFenceEntryLogId] Int
 * @property [userId] Int
 * @property [geoFenceId] Int
 * @property [exitTime] LocalDateTime
 */
data class ExitGeoFence(
    val geoFenceEntryLogId: Int,
    val userId: Int,
    val geoFenceId: Int,
    val exitTime: LocalDateTime,
)
