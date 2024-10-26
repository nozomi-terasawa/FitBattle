package org.example.project.usecases.user

import org.example.project.domain.repository.UserRepository
import org.example.project.domain.utility.hashWithSHA256
import org.example.project.infrastructure.auth.AuthJwt
import org.example.project.infrastructure.serializationData.UserLoginReq
import org.example.project.infrastructure.serializationData.UserLoginRes

class UserLoginUseCase(
    private val userRepository: UserRepository,
    private val authJwt: AuthJwt,
) {
    operator fun invoke(user: UserLoginReq): UserLoginRes {
        val value = userRepository.login(user.email)

        return if (value.passwordHash == user.password.hashWithSHA256()) {
            val token = authJwt.getToken(value.email)
            UserLoginRes(
                name = value.name,
                email = value.email,
                token = token,
            )
        } else {
            UserLoginRes()
        }
    }
}
