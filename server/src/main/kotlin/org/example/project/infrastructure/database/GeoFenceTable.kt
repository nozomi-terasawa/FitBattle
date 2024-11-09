package org.example.project.infrastructure.database

import org.jetbrains.exposed.sql.Table

object GeoFenceTable : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", length = 50).uniqueIndex()
    val latitude = double("latitude")
    val longitude = double("longitude")
    val radius = float("radius")
    override val primaryKey = PrimaryKey(id)
}
