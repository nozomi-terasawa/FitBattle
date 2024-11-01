package org.example.project.domain.entities.user

data class User(
    val userId: Int = -1,
    val name: String = "",
    val email: String = "",
    val passwordHash: String = "",
)
