package org.example.project.infrastructure.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object GeoFenceEntryLogTable : Table() {
    val id = integer("id").autoIncrement()
    val userId = reference("user_id", UserTable.id)
    val geoFenceId = reference("geofence_id", GeoFenceTable.id)
    val entryTime = datetime("entry_time")
    val exitTime = datetime("exit_Time").nullable()
    override val primaryKey = PrimaryKey(id)
}
