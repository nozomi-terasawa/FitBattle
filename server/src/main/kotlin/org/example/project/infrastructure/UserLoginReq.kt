package org.example.project.infrastructure

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginReq(
    val email: String,
    val password: String
)
