package org.example.project.infrastructure.routes

import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.project.infrastructure.serializationData.PassedReq
import org.example.project.usecases.passed.TodayPassedUserGetUseCase

fun Routing.passedRoutes(todayPassedUserGetUseCase: TodayPassedUserGetUseCase) {
    route("/api/v1/passed") {
        // JTW認証が必要
        authenticate("auth-jwt") {
            get {
                val req = call.receive<PassedReq>()
                val res = todayPassedUserGetUseCase(req.userId, req.timestamp)
                call.respond(status = HttpStatusCode.OK, message = res)
            }
            get("/all") {
                call.respondText("Get all passed")
            }
        }
    }
}
