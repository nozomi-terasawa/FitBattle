package org.example.project.infrastructure.database

import kotlinx.datetime.LocalDateTime
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
            GeoFenceTable,
            GeoFenceEntryLogTable,
            FitnessTable,
        )
        try {
            for (i in 1..20) {
                UserTable.insert {
                    it[name] = "test$i"
                    it[email] = "test$i@gmail.com"
                    it[passwordHash] = "test"
                }
            }
            GeoFenceTable.insert {
                it[name] = "スマプロ本部"
                it[latitude] = 36.528741
                it[longitude] = 136.628184
                it[radius] = 100f
            }
            GeoFenceTable.insert {
                it[name] = "金沢工業大学"
                it[latitude] = 36.530455
                it[longitude] = 136.627746
                it[radius] = 100f
            }
            GeoFenceTable.insert {
                it[name] = "金沢南総合運動公園"
                it[latitude] = 36.539305
                it[longitude] = 136.642862
                it[radius] = 100f
            }
            GeoFenceTable.insert {
                it[name] = "金沢市総合体育館"
                it[latitude] = 36.539971
                it[longitude] = 136.650184
                it[radius] = 100f
            }
            GeoFenceTable.insert {
                it[name] = "久安運動広場"
                it[latitude] = 36.533278
                it[longitude] = 136.630635
                it[radius] = 100f
            }

            GeoFenceTable.insert {
                it[name] = "額谷運動広場"
                it[latitude] = 36.509345
                it[longitude] = 136.629287
                it[radius] = 100f
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
        }
    }
}
