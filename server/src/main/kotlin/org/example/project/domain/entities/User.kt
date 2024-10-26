package org.example.project.domain.entities

data class User(
    val name: String = "",
    val email: String = "",
    val passwordHash: String = "",
)
