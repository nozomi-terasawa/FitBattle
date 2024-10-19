package org.example.project.infrastructure.routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.delete
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.example.project.infrastructure.UserCreateReq
import org.example.project.infrastructure.UserLoginReq
import org.example.project.usecases.UserCreateUseCase
import org.example.project.usecases.UserDeleteUseCase
import org.example.project.usecases.UserLogOutUseCase
import org.example.project.usecases.UserLoginUseCase


fun Routing.userRoutes(
    createUseCase: UserCreateUseCase,
    loginUseCase: UserLoginUseCase,
    logoutUseCase: UserLogOutUseCase,
    deleteUseCase: UserDeleteUseCase,
) {
    route("/api/v1/user") {
        post("/create") {
            val user = call.receive<UserCreateReq>()
            createUseCase(user)
            call.respond(status = HttpStatusCode.OK, message = user)
        }
        post("/login") {
            val user = call.receive<UserLoginReq>()
            val value = loginUseCase(user)
            if (value.email == "") {
                call.respond(status = HttpStatusCode.Unauthorized, message = "Invalid email or password")
            } else {
                call.respond(status = HttpStatusCode.OK, message = value)
            }
        }
        post("/logout") {
            logoutUseCase()
            call.respond(status = HttpStatusCode.OK, message = "User logged out")
        }
        delete("/delete") {
            deleteUseCase()
            call.respond(status = HttpStatusCode.OK, message = "User deleted")
        }
    }
}
