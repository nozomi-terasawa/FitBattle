package org.example.project.infrastructure.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object GeoFenceEntryLog : Table() {
    val id = integer("id").autoIncrement()
    val userId = reference("user_id", UserTable.id)
    val geoFenceId = reference("geofence_id", GeoFence.id)
    val entryTime = datetime("entry_time")
    val exitTime = datetime("exit_Time").nullable()
    override val primaryKey = PrimaryKey(id)
}
