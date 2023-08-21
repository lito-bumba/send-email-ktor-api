import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(emailHelper: EmailHelper) {
    val sendTo = System.getenv("EMAIL_SEND_TO")
    routing {
        get("/simple-email") {
            try {
                emailHelper.sendSimpleEmail(
                    sendTo = sendTo,
                    subject = "How To Send Email using Ktor Api",
                    message = "It's simply using the Apache Commons Email Library"
                )
                call.respond(HttpStatusCode.OK, "Simple Email Sent Successfully")
            } catch (exception: Exception) {
                call.respondText("${exception.message}")
            }
        }
        get("/email-with-template") {
            try {
                val htmlTemplate = "<h1>It's simply using the Apache Commons Email Library</h1>" +
                        "<p>Because with Kotin, everything get easy</p><br>" +
                        "<img src=\"https://cafeinacodificada.com.br/wp-content/uploads/2019/02/kotlin.jpg\"/>"

                emailHelper.sendEmailWithTemplate(
                    sendTo = sendTo,
                    subject = "How To Send Email using Ktor Api",
                    htmlTemplate = htmlTemplate
                )
                call.respond(HttpStatusCode.OK, "Email with Template Sent Successfully")
            } catch (exception: Exception) {
                call.respondText("${exception.message}")
            }
        }
    }
}