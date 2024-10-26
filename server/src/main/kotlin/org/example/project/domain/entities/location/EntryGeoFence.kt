package org.example.project.domain.entities.location

import kotlinx.datetime.LocalDateTime

/**
 * EntryGeoFence entity
 * @property [userId] Int
 * @property [geoFenceId] Int
 * @property [entryTime] LocalDateTime
 */
data class EntryGeoFence(
    val userId: Int,
    val geoFenceId: Int,
    val entryTime: LocalDateTime,
)
