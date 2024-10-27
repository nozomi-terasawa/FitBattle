package org.example.project.usecases.location

import kotlinx.datetime.toKotlinLocalDateTime
import org.example.project.domain.entities.location.EntryGeoFence
import org.example.project.domain.repository.GeoFenceRepository
import org.example.project.infrastructure.serializationData.EntryGeoFenceReq
import org.example.project.infrastructure.serializationData.EntryGeoFenceRes
import org.example.project.infrastructure.serializationData.MemberInfo
import java.time.OffsetDateTime

class EntryGeofenceUseCase(
    private val geoFenceRepository: GeoFenceRepository,
) {
    operator fun invoke(entryGeoFenceReq: EntryGeoFenceReq): EntryGeoFenceRes {
        val offsetDateTime = OffsetDateTime.parse(entryGeoFenceReq.entryTime)
        val localDateTime = offsetDateTime.toLocalDateTime().toKotlinLocalDateTime()

        val geoFenceEntryLogId =
            geoFenceRepository.entry(
                entryGeoFence =
                    EntryGeoFence(
                        userId = entryGeoFenceReq.userId,
                        geoFenceId = entryGeoFenceReq.geoFenceId,
                        entryTime = localDateTime,
                    ),
            )

        if (geoFenceEntryLogId == -1) {
            return EntryGeoFenceRes(
                success = false,
                token = "token",
                geoFenceEntryLogId = -1,
                passingMember = emptyList(),
            )
        }

        return EntryGeoFenceRes(
            success = true,
            geoFenceEntryLogId = geoFenceEntryLogId,
            passingMember =
                listOf(
                    MemberInfo(
                        id = 1,
                        name = "name",
                        iconUrl = "iconUrl",
                    ),
                ),
            token = "token",
        )
    }
}
