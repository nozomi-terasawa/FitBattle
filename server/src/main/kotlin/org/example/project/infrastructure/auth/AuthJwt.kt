package org.example.project.infrastructure.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import org.example.project.domain.repository.AuthRepository
import java.util.*

/**
 * JWTを管理するオブジェクト
 */
class AuthJwt(
    private val secret: String,
    private val issuer: String,
    private val audience: String,
    private val realm: String,
) : AuthRepository {
    /**
     * 認証システムを構築する
     * @return [JWTVerifier]
     */
    override fun verifier(): JWTVerifier =
        JWT
            .require(Algorithm.HMAC256(secret))
            .withAudience(audience)
            .withIssuer(issuer)
            .build()

    /**
     * ユーザー情報を元に、有効なトークンを生成する
     * @param [user] ユーザーの情報.
     * @return [String] tokenを返却する.
     */
    override fun getToken(user: String): String =
        JWT
            .create()
            .withSubject("Authentication")
            .withIssuer(issuer)
            .withClaim("userEmail", user)
            .withExpiresAt(Date(System.currentTimeMillis() + 60000))
            .sign(Algorithm.HMAC256(secret))
}
