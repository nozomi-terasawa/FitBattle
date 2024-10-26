package org.example.project.infrastructure.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object FitnessTable : Table() {
    val id = integer("id").autoIncrement()
    val userId = reference("user_id", UserTable.id)
    val calories = float("calories")
    val timestamp = datetime("timestamp")
    override val primaryKey = PrimaryKey(id)
}
