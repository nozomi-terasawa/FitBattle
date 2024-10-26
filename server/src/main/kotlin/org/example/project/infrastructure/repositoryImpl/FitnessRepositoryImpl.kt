package org.example.project.infrastructure.repositoryImpl

import org.example.project.domain.entities.fitness.Fitness
import org.example.project.domain.repository.FitnessRepository
import org.example.project.infrastructure.database.FitnessTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class FitnessRepositoryImpl : FitnessRepository {
    override fun save(fitness: Fitness): Boolean {
        try {
            transaction {
                FitnessTable.insert {
                    it[userId] = fitness.userId
                    it[calories] = fitness.calories
                    it[timestamp] = fitness.timestamp
                }
            }
        } catch (e: Exception) {
            println("Error during fitness creation: ${e.message}")
            return false
        }
        return true
    }

    override fun get(fitness: Fitness): List<Fitness> {
        TODO("Not yet implemented")
    }

    override fun delete(fitness: Fitness): Boolean {
        TODO("Not yet implemented")
    }

    override fun update(fitness: Fitness): Boolean {
        TODO("Not yet implemented")
    }
}
