package org.example.project.infrastructure.test

import org.example.project.domain.entities.User
import org.example.project.domain.repository.UserRepository
import org.example.project.infrastructure.database.UserTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction


class TestUserRepositoryImpl() : UserRepository {
    override fun create(user: User) {
        transaction {
            UserTable.insert {
                it[name] = user.name
                it[email] = user.email
                it[passwordHash] = user.passwordHash
            }
        }
    }

    override fun login(email: String): User {
        return try {
            val value = transaction {
                val query = UserTable.email eq email
                UserTable.selectAll().where(query).singleOrNull()
            }
            if (value != null) {
                User(
                    value[UserTable.name],
                    value[UserTable.email],
                    value[UserTable.passwordHash]
                )
            } else {
                println("No user found with email: $email")
                User() // デフォルト値を返す、またはエラー処理をする
            }
        } catch (e: Exception) {
            println("Error during login: ${e.message}")
            User() // デフォルトの User を返すか、適切なエラーを返す
        }
    }

    override fun logout() {
        println("User logged out")
    }

    override fun delete() {
        println("User deleted")
    }
}
