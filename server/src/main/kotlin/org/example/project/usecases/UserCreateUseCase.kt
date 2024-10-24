package org.example.project.usecases

import org.example.project.domain.entities.User
import org.example.project.domain.repository.UserRepository
import org.example.project.infrastructure.UserCreateReq
import org.example.project.infrastructure.auth.AuthJwt
import java.security.MessageDigest

class UserCreateUseCase(
    private val userRepository: UserRepository,
    private val authJwt: AuthJwt
) {
    operator fun invoke(user: UserCreateReq):String {
        fun String.hashWithSHA256(): String {
            val bytes = this.toByteArray()
            val md = MessageDigest.getInstance("SHA-256")
            val digest = md.digest(bytes)
            return digest.fold("") { str, it -> str + "%02x".format(it) }
        }

        val userId = userRepository.create(
            User(
                user.name,
                user.email,
                user.password.hashWithSHA256()
            )
        )
        return if (userId != -1) {
            authJwt.getToken(userId.toString())
        } else{
            ""
        }
    }
}




