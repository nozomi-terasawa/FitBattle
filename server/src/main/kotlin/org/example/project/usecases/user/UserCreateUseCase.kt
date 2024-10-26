package org.example.project.usecases.user

import org.example.project.domain.entities.user.User
import org.example.project.domain.repository.UserRepository
import org.example.project.domain.utility.hashWithSHA256
import org.example.project.infrastructure.UserCreateReq
import org.example.project.infrastructure.auth.AuthJwt

class UserCreateUseCase(
    private val userRepository: UserRepository,
    private val authJwt: AuthJwt,
) {
    operator fun invoke(user: UserCreateReq): String {
        val userId =
            userRepository.create(
                User(
                    user.name,
                    user.email,
                    user.password.hashWithSHA256(),
                ),
            )
        return if (userId != -1) {
            authJwt.getToken(userId.toString())
        } else {
            ""
        }
    }
}
