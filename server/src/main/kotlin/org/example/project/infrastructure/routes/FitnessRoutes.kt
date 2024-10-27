package org.example.project.infrastructure.routes

import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.project.infrastructure.serializationData.GetFitnessReq
import org.example.project.infrastructure.serializationData.SaveFitnessReq
import org.example.project.usecases.fitness.GetFitnessUseCase
import org.example.project.usecases.fitness.SaveFitnessUseCase

fun Routing.fitnessRoutes(
    saveFitnessUseCase: SaveFitnessUseCase,
    getFitnessUseCase: GetFitnessUseCase,
) {
    route("/api/v1/fitness") {
        post("/save") {
            val fitness = call.receive<SaveFitnessReq>()
            val value = saveFitnessUseCase(fitness)
            if (value.success) {
                call.respond(HttpStatusCode.Created, value)
            } else {
                call.respond(HttpStatusCode.BadRequest, value)
            }
        }
        get {
            val userId = call.receive<GetFitnessReq>()
            val fitness = getFitnessUseCase(userId)
            if (fitness.success) {
                call.respond(HttpStatusCode.OK, fitness)
            } else {
                call.respond(HttpStatusCode.NotFound, message = fitness)
            }
        }
    }
}
