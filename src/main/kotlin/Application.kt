import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(
        factory = Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    initialConfigs()
    val userAuth = UserAuth(
        hostName = System.getenv("HOST_NAME"),
        email = System.getenv("EMAIL_AUTH"),
        password = System.getenv("PASSWORD")
    )
    configureRouting(EmailHelper(userAuth))
}