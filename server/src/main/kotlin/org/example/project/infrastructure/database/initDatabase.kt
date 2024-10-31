package org.example.project.infrastructure.database

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
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
            GeoFenceTable,
            GeoFenceEntryLogTable,
            FitnessTable,
        )
        /*try {
            for (i in 1..20) {
                UserTable.insert {
                    it[name] = "test$i"
                    it[email] = "test$i@gmail.com"
                    it[passwordHash] = "test"
                }
            }
            GeoFenceTable.insert {
                it[name] = "Tokyo"
                it[latitude] = 35.681236
                it[longitude] = 139.767125
                it[radius] = 100.0
            }
            GeoFenceTable.insert {
                it[name] = "Osaka"
                it[latitude] = 34.693737
                it[longitude] = 135.502165
                it[radius] = 100.0
            }
            GeoFenceTable.insert {
                it[name] = "Nagoya"
                it[latitude] = 35.181467
                it[longitude] = 136.906398
                it[radius] = 100.0
            }
            GeoFenceEntryLogTable.insert {
                it[userId] = 1
                it[geoFenceId] = 2
                it[entryTime] = LocalDateTime.parse("2024-10-25T12:30:00")
                it[exitTime] = LocalDateTime.parse("2024-10-25T12:30:00")
            }

            GeoFenceEntryLogTable.insert {
                it[userId] = 2
                it[geoFenceId] = 2
                it[entryTime] = LocalDateTime.parse("2024-10-25T12:30:00")
                it[exitTime] = LocalDateTime.parse("2024-10-25T12:30:00")
            }

            GeoFenceEntryLogTable.insert {
                it[userId] = 3
                it[geoFenceId] = 2
                it[entryTime] = LocalDateTime.parse("2024-10-25T14:00:00")
                it[exitTime] = LocalDateTime.parse("2024-10-25T14:30:00")
            }

            GeoFenceEntryLogTable.insert {
                it[userId] = 4
                it[geoFenceId] = 1
                it[entryTime] = LocalDateTime.parse("2024-10-25T14:00:00")
                it[exitTime] = LocalDateTime.parse("2024-10-25T14:30:00")
            }

            GeoFenceEntryLogTable.insert {
                it[userId] = 5
                it[geoFenceId] = 2
                it[entryTime] = LocalDateTime.parse("2024-10-25T15:00:00")
                it[exitTime] = LocalDateTime.parse("2024-10-25T16:00:00")
            }

            GeoFenceEntryLogTable.insert {
                it[userId] = 6
                it[geoFenceId] = 2
                it[entryTime] = LocalDateTime.parse("2024-10-25T12:00:00")
                it[exitTime] = LocalDateTime.parse("2024-10-25T16:00:00")
            }

            // ユーザーID 7〜16
            GeoFenceEntryLogTable.insert {
                it[userId] = 7
                it[geoFenceId] = 2
                it[entryTime] = LocalDateTime.parse("2024-10-25T10:00:00")
                it[exitTime] = LocalDateTime.parse("2024-10-25T11:00:00")
            }

            GeoFenceEntryLogTable.insert {
                it[userId] = 8
                it[geoFenceId] = 2
                it[entryTime] = LocalDateTime.parse("2024-10-25T11:30:00")
                it[exitTime] = LocalDateTime.parse("2024-10-25T12:30:00")
            }

            GeoFenceEntryLogTable.insert {
                it[userId] = 9
                it[geoFenceId] = 1
                it[entryTime] = LocalDateTime.parse("2024-10-25T13:00:00")
                it[exitTime] = LocalDateTime.parse("2024-10-25T13:30:00")
            }

            GeoFenceEntryLogTable.insert {
                it[userId] = 10
                it[geoFenceId] = 1
                it[entryTime] = LocalDateTime.parse("2024-10-25T14:00:00")
                it[exitTime] = null // 退場時間が未定
            }

            GeoFenceEntryLogTable.insert {
                it[userId] = 11
                it[geoFenceId] = 2
                it[entryTime] = LocalDateTime.parse("2024-10-25T15:30:00")
                it[exitTime] = LocalDateTime.parse("2024-10-25T16:30:00")
            }

            GeoFenceEntryLogTable.insert {
                it[userId] = 12
                it[geoFenceId] = 2
                it[entryTime] = LocalDateTime.parse("2024-10-25T16:00:00")
                it[exitTime] = LocalDateTime.parse("2024-10-25T17:00:00")
            }

            GeoFenceEntryLogTable.insert {
                it[userId] = 13
                it[geoFenceId] = 1
                it[entryTime] = LocalDateTime.parse("2024-10-25T12:00:00")
                it[exitTime] = LocalDateTime.parse("2024-10-25T12:45:00")
            }

            GeoFenceEntryLogTable.insert {
                it[userId] = 14
                it[geoFenceId] = 3
                it[entryTime] = LocalDateTime.parse("2024-10-25T13:30:00")
                it[exitTime] = LocalDateTime.parse("2024-10-25T14:30:00")
            }

            GeoFenceEntryLogTable.insert {
                it[userId] = 15
                it[geoFenceId] = 3
                it[entryTime] = LocalDateTime.parse("2024-10-25T14:00:00")
                it[exitTime] = LocalDateTime.parse("2024-10-25T15:00:00")
            }

            GeoFenceEntryLogTable.insert {
                it[userId] = 16
                it[geoFenceId] = 3
                it[entryTime] = LocalDateTime.parse("2024-10-25T12:30:00")
                it[exitTime] = LocalDateTime.parse("2024-10-25T13:30:00")
            }
        } catch (e: Exception) {
            println("Error during database initialization: ${e.message}")
        }*/
    }
}
