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
                    subject = "Simple Email using Ktor Api",
                    message = "It get simple using the Apache Commons Email Library"
                )
                call.respond(HttpStatusCode.OK, "Simple Email Sent Successfully")
            } catch (exception: Exception) {
                call.respondText("${exception.message}")
            }
        }
        get("/email-with-template") {
            try {
                val htmlTemplateFile = getHtmlFromResource("EmailTemplate.html")
                val htmlTemplate = htmlTemplateFile.replace("\$emailTo", sendTo)

                emailHelper.sendEmailWithTemplate(
                    sendTo = sendTo,
                    subject = "Email with Template using Ktor Api",
                    htmlTemplate = htmlTemplate
                )
                call.respond(HttpStatusCode.OK, "Email with Template Sent Successfully")
            } catch (exception: Exception) {
                call.respondText("${exception.message}")
            }
        }
    }
}