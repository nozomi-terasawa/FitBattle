package org.example.project.infrastructure

import kotlinx.serialization.Serializable

/**
 * ジオフェンスに入室したことを伝える
 * @param [userId] ユーザーの識別ID
 * @param [geoFenceId] ジオフェンスの識別ID
 * @param [entryTime] 入場時刻
 */
@Serializable
data class EntryGeoFenceReq(
    val userId: Int,
    val geoFenceId: Int,
    val entryTime: String,
)
