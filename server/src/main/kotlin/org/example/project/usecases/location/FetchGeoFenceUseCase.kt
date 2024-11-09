package org.example.project.usecases.location

import org.example.project.domain.repository.GeoFenceRepository
import org.example.project.infrastructure.serializationData.FetchGeoFenceRes
import org.example.project.infrastructure.serializationData.GeoFenceInfoRes

class FetchGeoFenceUseCase(
    private val geoFenceRepository: GeoFenceRepository,
) {
    operator fun invoke(): FetchGeoFenceRes{
        return FetchGeoFenceRes(
            success = true,
            geoFence = geoFenceRepository.fetchGeoFenceInfo().map {
                GeoFenceInfoRes(
                    id = it.id,
                    name = it.name,
                    latitude = it.latitude,
                    longitude = it.longitude,
                    radius = it.radius.toFloat(),
                )
            }
        )
    }
}
