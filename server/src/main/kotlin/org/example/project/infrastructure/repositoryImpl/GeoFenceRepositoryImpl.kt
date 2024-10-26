package org.example.project.infrastructure.repositoryImpl

import org.example.project.domain.entities.location.EntryGeoFence
import org.example.project.domain.entities.location.ExitGeoFence
import org.example.project.domain.repository.GeoFenceRepository
import org.example.project.infrastructure.database.GeoFenceEntryLogTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class GeoFenceRepositoryImpl : GeoFenceRepository {
    override fun entry(entryGeoFence: EntryGeoFence): Int {
        val id: Int?
        try {
            id =
                transaction {
                    GeoFenceEntryLogTable
                        .insert {
                            it[userId] = entryGeoFence.userId
                            it[geoFenceId] = entryGeoFence.geoFenceId
                            it[entryTime] = entryGeoFence.entryTime
                        }.getOrNull(GeoFenceEntryLogTable.id)
                }
        } catch (e: Exception) {
            return -1
        }
        return id ?: -1
    }

    override fun exit(exitGeoFence: ExitGeoFence): Boolean {
        try {
            transaction {
                GeoFenceEntryLogTable.update(where = { GeoFenceEntryLogTable.id eq exitGeoFence.geoFenceEntryLogId }) {
                    it[exitTime] = exitGeoFence.exitTime
                }
            }
        } catch (e: Exception) {
            return false
        }
        return true
    }
}
