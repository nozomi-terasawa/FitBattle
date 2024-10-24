package org.example.project.infrastructure

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRes(
    val name: String = "",
    val email: String = "",
    val token: String = "",
)
