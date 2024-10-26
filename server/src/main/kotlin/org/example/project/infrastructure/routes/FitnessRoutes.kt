package org.example.project.infrastructure.routes

import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.project.infrastructure.serializationData.SaveFitnessReq
import org.example.project.usecases.fitness.SaveFitnessUseCase

fun Routing.fitnessRoutes(saveFitnessUseCase: SaveFitnessUseCase) {
    route("/api/v1/fitness") {
        post("/save") {
            val fitness = call.receive<SaveFitnessReq>()
            val value = saveFitnessUseCase.save(fitness)
            if (value.success) {
                call.respond(HttpStatusCode.Created, value)
            } else {
                call.respond(HttpStatusCode.BadRequest, value)
            }
        }
    }
}
