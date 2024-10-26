package org.example.project.usecases.fitness

import org.example.project.domain.entities.fitness.Fitness
import org.example.project.domain.repository.FitnessRepository
import org.example.project.domain.utility.toLocalDateTime
import org.example.project.infrastructure.serializationData.SaveFitnessReq
import org.example.project.infrastructure.serializationData.SaveFitnessRes

class SaveFitnessUseCase(
    private val fitnessRepository: FitnessRepository,
) {
    fun save(fitness: SaveFitnessReq): SaveFitnessRes =
        SaveFitnessRes(
            success =
                fitnessRepository.save(
                    Fitness(
                        userId = fitness.userId,
                        calories = fitness.calories,
                        timestamp = fitness.timestamp.toLocalDateTime(),
                    ),
                ),
        )
}
