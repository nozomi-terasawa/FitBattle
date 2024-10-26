package org.example.project.infrastructure.repositoryImpl

import org.example.project.domain.repository.PassedUserRepository
import org.example.project.infrastructure.database.GeoFenceEntryLogTable
import org.example.project.infrastructure.serializationData.PassedReq
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class PassedUserRepositoryImpl : PassedUserRepository {
    override fun getPassedUsers(passedRwq: PassedReq): List<Int> {
        val encounteredUsers = mutableListOf<Int>()
        try {
            val targetUserId = passedRwq.userId
            transaction {
                GeoFenceEntryLogTable.alias("a").let { logA ->
                    GeoFenceEntryLogTable.alias("b").let { logB ->
                        val query =
                            logA
                                .innerJoin(
                                    logB,
                                    onColumn = { logA[GeoFenceEntryLogTable.geoFenceId] },
                                    otherColumn = { logB[GeoFenceEntryLogTable.geoFenceId] },
                                ).selectAll()
                                .where {
                                    (logA[GeoFenceEntryLogTable.geoFenceId] eq logB[GeoFenceEntryLogTable.geoFenceId]) and
                                        // 同じユーザーでないことを確認
                                        (logA[GeoFenceEntryLogTable.userId] neq logB[GeoFenceEntryLogTable.userId]) and
                                        // 特定のユーザーとすれ違った相手ユーザーのみを取得
                                        (
                                            (logA[GeoFenceEntryLogTable.userId] eq targetUserId) or
                                                (logB[GeoFenceEntryLogTable.userId] eq targetUserId)
                                        ) and
                                        // logAの入場時間がlogBの退場時間（nullの場合も含む）と重なっている
                                        (logA[GeoFenceEntryLogTable.entryTime] lessEq (logB[GeoFenceEntryLogTable.exitTime])) and
                                        // logBの入場時間がlogAの退場時間（nullの場合も含む）と重なっている
                                        ((logA[GeoFenceEntryLogTable.exitTime]) greaterEq logB[GeoFenceEntryLogTable.entryTime])
                                }

                        query.forEach { resultRow ->
                            val userAId = resultRow[logA[GeoFenceEntryLogTable.userId]]
                            val userBId = resultRow[logB[GeoFenceEntryLogTable.userId]]
                            val otherUserId = if (userAId == targetUserId) userBId else userAId
                            // リストに追加（重複を避けるためdistinct適用）
                            encounteredUsers.add(otherUserId)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            TODO()
        }
        return encounteredUsers.distinct().sorted()
    }
}
