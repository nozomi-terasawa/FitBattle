package org.example.project.infrastructure.database

import org.jetbrains.exposed.sql.Table

object GeoFence : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", length = 50)
    val latitude = double("latitude")
    val longitude = double("longitude")
    val radius = double("radius")
    override val primaryKey = PrimaryKey(id)
}
