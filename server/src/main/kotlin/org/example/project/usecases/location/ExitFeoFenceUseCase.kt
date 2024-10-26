package org.example.project.usecases.location

import org.example.project.infrastructure.ExitFeoFenceReq
import org.example.project.infrastructure.ExitGeoFenceRes

class ExitFeoFenceUseCase {
    operator fun invoke(entryGeoFenceReq: ExitFeoFenceReq): ExitGeoFenceRes =
        ExitGeoFenceRes(
            success = true,
        )
}
