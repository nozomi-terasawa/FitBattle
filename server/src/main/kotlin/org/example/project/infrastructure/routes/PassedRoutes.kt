package org.example.project.infrastructure.routes

import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.project.domain.repository.PassedUserRepository
import org.example.project.infrastructure.serializationData.PassedReq

fun Routing.passedRoutes(passedUserRepository: PassedUserRepository) {
    route("/api/v1/passed") {
        get("/get") {
            val req = call.receive<PassedReq>()
            val res = passedUserRepository.getPassedUsers(req)
            call.respond(status = HttpStatusCode.OK, message = res)
        }
        get("/getAll") {
            call.respondText("Get all passed")
        }
    }
}
