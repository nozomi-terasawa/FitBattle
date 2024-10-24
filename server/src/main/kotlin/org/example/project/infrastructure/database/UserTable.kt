package org.example.project.infrastructure.database

import org.jetbrains.exposed.sql.Table

object UserTable : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", length = 50)
    val email = varchar("email", length = 50)
    val passwordHash = varchar("password", length = 64)
    override val primaryKey = PrimaryKey(id)
}
