package org.example.project.infrastructure.repositoryImpl

import org.example.project.domain.entities.fitness.Fitness
import org.example.project.domain.repository.FitnessRepository
import org.example.project.infrastructure.database.FitnessTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
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

    override fun get(userId: Int): Fitness {
        var value = Fitness()
        try {
            val query = FitnessTable.userId eq userId
            transaction {
                val result = FitnessTable.selectAll().where(query).singleOrNull()
                if (result != null) {
                    value =
                        Fitness(
                            result[FitnessTable.userId],
                            result[FitnessTable.calories],
                            result[FitnessTable.timestamp],
                        )
                }
            }
        } catch (e: Exception) {
            println("Error during fitness get: ${e.message}")
            return Fitness()
        }
        return value
    }

    override fun delete(fitness: Fitness): Boolean {
        TODO("Not yet implemented")
    }

    override fun update(fitness: Fitness): Boolean {
        TODO("Not yet implemented")
    }
}
