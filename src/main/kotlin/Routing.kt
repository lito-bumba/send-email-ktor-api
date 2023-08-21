import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/simple-email") {
            try {
                call.respond(HttpStatusCode.OK, "Simple Email Sent Successfully")
            } catch (exception: Exception) {
                call.respondText("Error: ${exception.message}")
            }
        }
        get("/email-with-template") {
            try {
                call.respond(HttpStatusCode.OK, "Email with Template Sent Successfully")
            } catch (exception: Exception) {
                call.respondText("Error: ${exception.message}")
            }
        }
    }
}