package org.example.project.infrastructure.routes

import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.project.infrastructure.SaveFitnessReq
import org.example.project.usecases.fitness.SaveFitnessUseCase

fun Routing.fitnessRoutes(saveFitnessUseCase: SaveFitnessUseCase) {
    route("/api/v1/fitness") {
        post("/save") {
            val fitness = call.receive<SaveFitnessReq>()
            val value = saveFitnessUseCase.save(fitness)
            if (value) {
                call.respond(status = HttpStatusCode.OK, message = "Fitness data saved")
            } else {
                call.respond(status = HttpStatusCode.InternalServerError, message = "Failed to save fitness data")
            }
        }
    }
}
