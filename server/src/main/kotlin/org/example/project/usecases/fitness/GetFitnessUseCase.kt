package org.example.project.usecases.fitness

import org.example.project.domain.repository.FitnessRepository
import org.example.project.infrastructure.serializationData.GetFitnessReq
import org.example.project.infrastructure.serializationData.GetFitnessRes
import org.example.project.infrastructure.serializationData.SaveFitnessReq

class GetFitnessUseCase(
    private val fitnessRepository: FitnessRepository,
) {
    operator fun invoke(req: GetFitnessReq): GetFitnessRes {
        val fitness = fitnessRepository.get(req.userId)
        return if (fitness.userId == -1) {
            GetFitnessRes(success = false, fitness = null)
        } else {
            GetFitnessRes(success = true, fitness = SaveFitnessReq(fitness.userId, fitness.calories, fitness.timestamp.toString()))
        }
    }
}
