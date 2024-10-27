package org.example.project.domain.repository

import org.example.project.domain.entities.location.EntryGeoFence
import org.example.project.domain.entities.location.ExitGeoFence

/**
 * GeoFenceRepository interface
 * @property [entry] function to entry geoFence
 * @property [exit] function to exit geoFence
 */
interface GeoFenceRepository {
    fun entry(entryGeoFence: EntryGeoFence): Int

    fun exit(exitGeoFence: ExitGeoFence): Boolean
}
