package org.example.project.usecases

import org.example.project.domain.repository.UserRepository


class UserLogOutUseCase(
    private val userRepository: UserRepository

) {
    operator fun invoke() {
        userRepository.logout()
    }
}
