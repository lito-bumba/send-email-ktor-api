import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.initialConfigs() {
    install(ContentNegotiation) {
        json()
    }
    routing {
        get("/") {
            call.respondText("Api Running")
        }
    }
}