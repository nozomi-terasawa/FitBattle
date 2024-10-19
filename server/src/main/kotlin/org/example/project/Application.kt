package org.example.project

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.netty.EngineMain
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.routing
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

    install(ContentNegotiation){
        json()
    }

    initDatabase()

    val testUserRepositoryImpl = TestUserRepositoryImpl()
    val userCreateUseCase = UserCreateUseCase(testUserRepositoryImpl)
    val userLoginUseCase = UserLoginUseCase(testUserRepositoryImpl)
    val userLogoutUseCase = UserLogOutUseCase(testUserRepositoryImpl)
    val userDeleteUseCase = UserDeleteUseCase(testUserRepositoryImpl)

    routing {
        userRoutes(
            userCreateUseCase,
            userLoginUseCase,
            userLogoutUseCase,
            userDeleteUseCase
        )
        locationRoutes()
    }
}
