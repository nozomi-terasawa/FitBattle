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
            UserTable
        )
    }


}
