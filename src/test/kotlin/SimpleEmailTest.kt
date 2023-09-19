import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals

class SimpleEmailTest {

    private val userAuth = UserAuth(
        hostName = System.getenv("HOST_NAME"),
        email = System.getenv("EMAIL_AUTH"),
        password = System.getenv("PASSWORD")
    )
    private val emailHelper = EmailHelper(userAuth)

    @Test
    fun `Api is Running`() = testApplication {
        application { initialConfigs() }
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Api Running", response.bodyAsText())
    }

    @Test
    fun sendSimpleEmail() = testApplication {
        application {
            configureRouting(emailHelper)
        }
        val response = client.get("/simple-email")
        assertEquals(HttpStatusCode.OK, response.status)
    }
}