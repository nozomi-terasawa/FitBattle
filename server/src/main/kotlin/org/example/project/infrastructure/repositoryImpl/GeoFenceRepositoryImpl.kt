package org.example.project.infrastructure.repositoryImpl

import org.example.project.domain.entities.location.EntryGeoFence
import org.example.project.domain.entities.location.ExitGeoFence
import org.example.project.domain.repository.GeoFenceRepository
import org.example.project.infrastructure.database.GeoFenceEntryLog
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class GeoFenceRepositoryImpl : GeoFenceRepository {
    override fun entry(entryGeoFence: EntryGeoFence): Int {
        val id: Int?
        try {
            id =
                transaction {
                    GeoFenceEntryLog
                        .insert {
                            it[userId] = entryGeoFence.userId
                            it[geoFenceId] = entryGeoFence.geoFenceId
                            it[entryTime] = entryGeoFence.entryTime
                        }.getOrNull(GeoFenceEntryLog.id)
                }
        } catch (e: Exception) {
            return -1
        }
        return id ?: -1
    }

    override fun exit(exitGeoFence: ExitGeoFence): Boolean {
        try {
            transaction {
                GeoFenceEntryLog.update(where = { GeoFenceEntryLog.id eq exitGeoFence.geoFenceEntryLogId }) {
                    it[exitTime] = exitGeoFence.exitTime
                }
            }
        } catch (e: Exception) {
            return false
        }
        return true
    }
}
