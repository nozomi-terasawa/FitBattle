package org.example.project.domain.repository

import org.example.project.domain.entities.fitness.Fitness

interface FitnessRepository {
    fun save(fitness: Fitness): Boolean

    fun get(fitness: Fitness): List<Fitness>

    fun delete(fitness: Fitness): Boolean

    fun update(fitness: Fitness): Boolean
}
