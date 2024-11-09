package org.example.project.infrastructure.repositoryImpl

import org.example.project.domain.entities.location.EntryGeoFence
import org.example.project.domain.entities.location.ExitGeoFence
import org.example.project.domain.entities.location.GeoFenceInfo
import org.example.project.domain.repository.GeoFenceRepository
import org.example.project.infrastructure.database.GeoFenceEntryLogTable
import org.example.project.infrastructure.database.GeoFenceTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
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

    override fun fetchGeoFenceInfo(): List<GeoFenceInfo> {
        try {
            var list: List<GeoFenceInfo> = emptyList()
            transaction {
                list = GeoFenceTable.selectAll().map {
                    GeoFenceInfo(
                        id = it[GeoFenceTable.id],
                        name = it[GeoFenceTable.name],
                        latitude = it[GeoFenceTable.latitude],
                        longitude = it[GeoFenceTable.longitude],
                        radius = it[GeoFenceTable.radius],
                    )
                }
            }
            return list
        }catch (e: Exception) {
            println("Error: $e")
            return emptyList()
        }
    }
}
