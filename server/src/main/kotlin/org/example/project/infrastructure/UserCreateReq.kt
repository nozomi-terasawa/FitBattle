package org.example.project.infrastructure

import kotlinx.serialization.Serializable

@Serializable
data class UserCreateReq(
    val name: String,
    val email: String,
    val password: String
)
