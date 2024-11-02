package org.example.project.domain.repository

import org.example.project.infrastructure.serializationData.MemberInfo

interface UserInfoRepository {
    fun getUserInfo(userId: Int): MemberInfo
}
