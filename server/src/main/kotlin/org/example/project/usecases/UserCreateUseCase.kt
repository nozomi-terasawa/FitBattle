package org.example.project.usecases

import org.example.project.domain.entities.User
import org.example.project.domain.repository.UserRepository
import org.example.project.infrastructure.UserCreateReq
import java.security.MessageDigest

class UserCreateUseCase(
    private val userRepository: UserRepository) {
    operator fun invoke(user: UserCreateReq) {
        fun String.hashWithSHA256(): String {
            val bytes = this.toByteArray()
            val md = MessageDigest.getInstance("SHA-256")
            val digest = md.digest(bytes)
            return digest.fold("") { str, it -> str + "%02x".format(it) }
        }
        userRepository.create(
            User(
                user.name,
                user.email,
                user.password.hashWithSHA256()
            )
        )
    }
}




