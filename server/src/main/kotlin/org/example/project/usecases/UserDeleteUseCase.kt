package org.example.project.usecases

import org.example.project.domain.repository.UserRepository

class UserDeleteUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke() {
        userRepository.delete()
    }
}
