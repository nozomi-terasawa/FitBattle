package org.example.project.infrastructure.serializationData

import kotlinx.serialization.Serializable

/**
 * ジオフェンスの退場を受け取る
 * @param [userId] ユーザー識別ID
 * @param [geoFenceId] ジオフェンスの識別ID
 * @param [exitTime] 退場した時間
 */
@Serializable
data class ExitFeoFenceReq(
    val geoFenceEntryLogId: Int,
    val userId: Int,
    val geoFenceId: Int,
    val exitTime: String,
)
