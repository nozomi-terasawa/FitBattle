package org.example.project.usecases.passed

import org.example.project.domain.repository.PassedUserRepository
import org.example.project.domain.repository.UserInfoRepository
import org.example.project.infrastructure.serializationData.MemberInfo
import org.example.project.infrastructure.serializationData.TodayPassedRes

/**
 * This class is a use case for getting the users that have passed the current user today.
 * @param passedUserRepository a [PassedUserRepository] interface.
 * @param userInfoRepository a [UserInfoRepository] interface.
 * @return a [TodayPassedRes] data class.
 */
class TodayPassedUserGetUseCase(
    private val passedUserRepository: PassedUserRepository,
    private val userInfoRepository: UserInfoRepository,
) {
    operator fun invoke(
        userId: Int,
        timestamp: String,
    ): TodayPassedRes {
        val passedUserList = mutableListOf<MemberInfo>()

        val passedUserIdList =
            passedUserRepository.getPassedUsers(
                userId = userId,
                timestamp = timestamp,
            )

        for (passedUserId in passedUserIdList) {
            val memberInfo = userInfoRepository.getUserInfo(passedUserId)
            passedUserList.add(memberInfo)
        }

        return TodayPassedRes(
            success = true,
            passedUser = passedUserList,
        )
    }
}
