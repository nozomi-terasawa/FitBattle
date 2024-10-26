package org.example.project.domain.entities.fitness

import kotlinx.datetime.LocalDateTime

data class Fitness(
    val userId: Int,
    val calories: Float,
    val timestamp: LocalDateTime,
)
