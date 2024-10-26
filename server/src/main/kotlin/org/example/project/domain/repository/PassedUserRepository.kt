package org.example.project.domain.repository

import org.example.project.infrastructure.serializationData.PassedReq

interface PassedUserRepository {
    fun getPassedUsers(passedRwq: PassedReq): List<Int>
}
