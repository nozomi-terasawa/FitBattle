package org.example.project.domain.entities.fitness

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class Fitness(
    val userId: Int = -1,
    val calories: Float = 0.0f,
    val timestamp: LocalDateTime =
        Clock.System.now().toLocalDateTime(
            timeZone = TimeZone.currentSystemDefault(),
        ),
)
