package org.example.project.infrastructure.routes

import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.project.infrastructure.serializationData.UserCreateReq
import org.example.project.infrastructure.serializationData.UserLoginReq
import org.example.project.usecases.user.UserCreateUseCase
import org.example.project.usecases.user.UserDeleteUseCase
import org.example.project.usecases.user.UserLogOutUseCase
import org.example.project.usecases.user.UserLoginUseCase

fun Routing.userRoutes(
    createUseCase: UserCreateUseCase,
    loginUseCase: UserLoginUseCase,
    logoutUseCase: UserLogOutUseCase,
    deleteUseCase: UserDeleteUseCase,
) {
    route("/api/v1/user") {
        post("/create") {
            val user = call.receive<UserCreateReq>()
            val value = createUseCase(user)
            if (value.userId == -1) {
                call.respond(status = HttpStatusCode.BadRequest, message = "User already exists")
            } else {
                call.respond(status = HttpStatusCode.Created, message = value)
            }
        }
        post("/login") {
            val user = call.receive<UserLoginReq>()
            val value = loginUseCase(user)
            if (value.userId == -1) {
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
