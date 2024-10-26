package org.example.project.infrastructure.routes

import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.example.project.infrastructure.EntryGeoFenceReq
import org.example.project.infrastructure.ExitFeoFenceReq
import org.example.project.usecases.location.EntryGeofenceUseCase
import org.example.project.usecases.location.ExitFeoFenceUseCase
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 *
 */
fun Routing.geoFenceRoutes(
    entryGeofenceUseCase: EntryGeofenceUseCase,
    exitFeoFenceUseCase: ExitFeoFenceUseCase,
) {
    route("/api/v1/location") {
        post("/geofence/entry") {
            val info = call.receive<EntryGeoFenceReq>()
            val res = entryGeofenceUseCase(info)
            call.respond(message = res)
        }

        post("/geofence/exit") {
            val info = call.receive<ExitFeoFenceReq>()
            val res = exitFeoFenceUseCase(info)
            call.respond(message = res)
        }

        val clients = ConcurrentHashMap<String, MutableMap<String, WebSocketServerSession>>()
        val clientSizes = ConcurrentHashMap<String, MutableStateFlow<Int>>()

        webSocket("/ws") {
            val parameters = call.request.queryParameters
            val roomID = parameters["roomID"] ?: return@webSocket close(CloseReason(CloseReason.Codes.CANNOT_ACCEPT, "roomID is missing"))

            send(content = "Location WebSocket for Room: $roomID")

            val uuid = UUID.randomUUID().toString()
            val roomClients = clients.getOrPut(roomID) { ConcurrentHashMap() }
            roomClients[uuid] = this

            val roomClientSize = clientSizes.getOrPut(roomID) { MutableStateFlow(0) }
            roomClientSize.value = roomClients.size

            val job =
                launch {
                    roomClientSize.collect { size ->
                        send("Client size in Room $roomID: $size")
                        send("Clients in Room $roomID: ${roomClients.keys.joinToString(", ")}")
                    }
                }

            try {
                for (frame in incoming) {
                    if (frame is Frame.Text) {
                        val message = frame.readText()
                        println("Received message from client $uuid in Room $roomID: $message")
                        roomClients.forEach { (clientId, clientSession) ->
                            if (clientId != uuid) {
                                clientSession.send("Broadcast in Room $roomID: $message")
                            }
                        }
                    }
                }
            } finally {
                roomClients.remove(uuid)
                if (roomClients.isEmpty()) {
                    clients.remove(roomID)
                    clientSizes.remove(roomID)
                } else {
                    roomClientSize.value = roomClients.size
                }
                job.cancel()
            }
        }
    }
}
