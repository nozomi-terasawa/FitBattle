package org.example.project.infrastructure.repositoryImpl

import org.example.project.domain.repository.UserInfoRepository
import org.example.project.infrastructure.database.UserTable
import org.example.project.infrastructure.serializationData.MemberInfo
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class UserInfoRepositoryImpl : UserInfoRepository {
    override fun getUserInfo(userId: Int): MemberInfo {
        var memberInfo = MemberInfo(-1, "", "")
        try {
            transaction {
                val query = UserTable.id eq userId
                val user = UserTable.selectAll().where { query }.singleOrNull()

                if (user != null) {
                    memberInfo = MemberInfo(user[UserTable.id], user[UserTable.name], "https://someurl.com")
                } else {
                    println("No user found for userId: $userId")
                }
            }
            return memberInfo
        } catch (e: Exception) {
            println("ErrorUserInfoRepositoryImpl: $e")
            return memberInfo
        }
    }
}
