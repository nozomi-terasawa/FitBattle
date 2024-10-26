package org.example.project.infrastructure.database

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

fun initDatabase() {
    val url = "jdbc:postgresql://localhost:5432/test_database"

    Database.connect(
        url = url,
        driver = "org.postgresql.Driver",
        user = "test",
        password = "test",
    )
    transaction {
        SchemaUtils.create(
            UserTable,
            GeoFence,
            GeoFenceEntryLog,
        )
        UserTable.insert {
            it[name] = "test1"
            it[email] = "test@gmail.com"
            it[passwordHash] = "test"
        }
        GeoFence.insert {
            it[name] = "Tokyo"
            it[latitude] = 35.681236
            it[longitude] = 139.767125
            it[radius] = 100.0
        }
    }
}
