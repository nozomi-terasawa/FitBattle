package org.example.project.usecases

import org.example.project.domain.repository.UserRepository
import org.example.project.infrastructure.UserLoginReq
import org.example.project.infrastructure.UserLoginRes
import org.example.project.infrastructure.auth.AuthJwt
import java.security.MessageDigest


class UserLoginUseCase(
    private val userRepository: UserRepository,
    private val authJwt: AuthJwt
) {
    operator fun invoke(user: UserLoginReq): UserLoginRes {
        fun String.hashWithSHA256(): String {
            val bytes = this.toByteArray()
            val md = MessageDigest.getInstance("SHA-256")
            val digest = md.digest(bytes)
            return digest.fold("") { str, it -> str + "%02x".format(it) }
        }

        val value = userRepository.login(user.email)

        return if (value.passwordHash == user.password.hashWithSHA256()) {
            val token = authJwt.getToken(value.email)
            UserLoginRes(
                name = value.name,
                email = value.email,
                token = token
            )
        } else {
            UserLoginRes()
        }
    }
}
