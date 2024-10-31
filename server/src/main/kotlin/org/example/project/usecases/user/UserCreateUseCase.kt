package org.example.project.usecases.user

import org.example.project.domain.entities.user.User
import org.example.project.domain.repository.UserRepository
import org.example.project.domain.utility.hashWithSHA256
import org.example.project.infrastructure.auth.AuthJwt
import org.example.project.infrastructure.serializationData.UserCreateReq
import org.example.project.infrastructure.serializationData.UserCreateRes

class UserCreateUseCase(
    private val userRepository: UserRepository,
    private val authJwt: AuthJwt,
) {
    operator fun invoke(user: UserCreateReq): UserCreateRes {
        val userId =
            userRepository.create(
                User(
                    name = user.name,
                    email = user.email,
                    passwordHash = user.password.hashWithSHA256(),
                ),
            )
        return if (userId != -1) {
            UserCreateRes(
                userId,
                authJwt.getToken(userId.toString()),
            )
        } else {
            UserCreateRes(-1, "")
        }
    }
}
