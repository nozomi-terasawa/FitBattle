package org.example.project.domain.repository

interface PassedUserRepository {
    fun getPassedUsers(
        userId: Int,
        timestamp: String,
    ): List<Int>
}
