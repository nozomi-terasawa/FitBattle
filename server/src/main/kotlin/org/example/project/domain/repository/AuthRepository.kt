package org.example.project.domain.repository

import com.auth0.jwt.JWTVerifier

interface AuthRepository {
    fun verifier(): JWTVerifier
    fun getToken(user:String): String
}
