package org.example.project.infrastructure.routes

import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Routing.locationRoutes() {
    route("/api/v1/location") {
        authenticate("auth-jwt") {
            get("/geofences") {
                call.respondText("Location")
            }
            get("/geofences/update") {
                call.respondText("Update")
            }
        }
    }
}
