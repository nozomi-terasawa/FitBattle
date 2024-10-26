package org.example.project.usecases.location

import kotlinx.datetime.toKotlinLocalDateTime
import org.example.project.domain.entities.location.ExitGeoFence
import org.example.project.domain.repository.GeoFenceRepository
import org.example.project.infrastructure.ExitFeoFenceReq
import org.example.project.infrastructure.ExitGeoFenceRes
import java.time.OffsetDateTime

class ExitFeoFenceUseCase(
    private val geoFenceRepository: GeoFenceRepository,
) {
    operator fun invoke(exitGeoFenceReq: ExitFeoFenceReq): ExitGeoFenceRes {
        val offsetDateTime = OffsetDateTime.parse(exitGeoFenceReq.exitTime)
        val localDateTime = offsetDateTime.toLocalDateTime().toKotlinLocalDateTime()
        val res =
            geoFenceRepository.exit(
                exitGeoFence =
                    ExitGeoFence(
                        geoFenceEntryLogId = exitGeoFenceReq.geoFenceEntryLogId,
                        userId = exitGeoFenceReq.userId,
                        geoFenceId = exitGeoFenceReq.geoFenceId,
                        exitTime = localDateTime,
                    ),
            )
        return ExitGeoFenceRes(
            success = res,
        )
    }
}
