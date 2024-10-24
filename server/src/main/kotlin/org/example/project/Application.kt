package org.example.project

import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.netty.EngineMain
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import org.example.project.infrastructure.auth.AuthJwt
import org.example.project.infrastructure.database.initDatabase
import org.example.project.infrastructure.routes.locationRoutes
import org.example.project.infrastructure.routes.userRoutes
import org.example.project.infrastructure.test.TestUserRepositoryImpl
import org.example.project.usecases.UserCreateUseCase
import org.example.project.usecases.UserDeleteUseCase
import org.example.project.usecases.UserLogOutUseCase
import org.example.project.usecases.UserLoginUseCase

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    val secret = environment.config.property("jwt.secret").getString()
    val issuer = environment.config.property("jwt.issuer").getString()
    val audience = environment.config.property("jwt.audience").getString()
    val myRealm = environment.config.property("jwt.realm").getString()

    val authJwt = AuthJwt(secret, issuer, audience, myRealm)

    install(ContentNegotiation) {
        json()
    }

    install(Authentication) {
        // JWTの設定
        jwt("auth-jwt") {
            realm = myRealm
            verifier(authJwt.verifier())
            validate {
                val name = it.payload.getClaim("userEmail").asString()
                if (name != null) {
                    JWTPrincipal(it.payload)
                } else {
                    null
                }
            }
            challenge { _, _ ->
                call.respond(status = HttpStatusCode.Unauthorized, message = "Unauthorized")
            }
        }
    }

    initDatabase()

    val testUserRepositoryImpl = TestUserRepositoryImpl()
    val userCreateUseCase = UserCreateUseCase(testUserRepositoryImpl, authJwt)
    val userLoginUseCase = UserLoginUseCase(testUserRepositoryImpl, authJwt)
    val userLogoutUseCase = UserLogOutUseCase(testUserRepositoryImpl)
    val userDeleteUseCase = UserDeleteUseCase(testUserRepositoryImpl)

    routing {
        userRoutes(
            userCreateUseCase,
            userLoginUseCase,
            userLogoutUseCase,
            userDeleteUseCase,
        )
        locationRoutes()
    }
}
