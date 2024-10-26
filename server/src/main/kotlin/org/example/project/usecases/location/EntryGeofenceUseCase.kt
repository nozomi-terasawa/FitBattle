package org.example.project.usecases.location

import org.example.project.infrastructure.EntryGeoFenceReq
import org.example.project.infrastructure.EntryGeoFenceRes
import org.example.project.infrastructure.MemberInfo

class EntryGeofenceUseCase {
    operator fun invoke(entryGeoFenceReq: EntryGeoFenceReq): EntryGeoFenceRes {
        val res =
            EntryGeoFenceRes(
                success = true,
                token = "test",
                passingMember =
                    listOf(
                        MemberInfo(
                            id = 3,
                            name = "こうき",
                            iconUrl = "",
                        ),
                        MemberInfo(
                            id = 4,
                            name = "佐々木",
                            iconUrl = "",
                        ),
                        MemberInfo(
                            id = 6,
                            name = "山田",
                            iconUrl = "",
                        ),
                    ),
            )
        return res
    }
}
