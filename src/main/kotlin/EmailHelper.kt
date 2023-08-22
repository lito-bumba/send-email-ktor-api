import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.EmailException
import org.apache.commons.mail.HtmlEmail
import org.apache.commons.mail.SimpleEmail

data class UserAuth(
    val hostName: String,
    val email: String,
    val password: String
)

class EmailHelper(
    private val userAuth: UserAuth
) {
    fun sendSimpleEmail(sendTo: String, subject: String, message: String) {
        try {
            val email = SimpleEmail()
            email.hostName = userAuth.hostName
            email.setAuthenticator(DefaultAuthenticator(userAuth.email, userAuth.password))
            email.isSSLOnConnect = true
            email.setFrom(userAuth.email)
            email.subject = subject
            email.setMsg(message)
            email.addTo(sendTo)
            email.send()
        } catch (exception: EmailException) {
            throw EmailException("Email Error: ${exception.message}")
        } catch (exception: Exception) {
            throw Exception("${exception.message}")
        }
    }

    fun sendEmailWithTemplate(sendTo: String, subject: String, htmlTemplate: String) {
        try {
            val htmlEmail = HtmlEmail()
            htmlEmail.hostName = userAuth.hostName
            htmlEmail.setAuthenticator(DefaultAuthenticator(userAuth.email, userAuth.password))
            htmlEmail.isSSLOnConnect = true
            htmlEmail.setFrom(userAuth.email)
            htmlEmail.subject = subject
            htmlEmail.setMsg(htmlTemplate)
            htmlEmail.addTo(sendTo)
            htmlEmail.send()
        } catch (exception: EmailException) {
            throw EmailException("Email Error: ${exception.message}")
        } catch (exception: Exception) {
            throw Exception("${exception.message}")
        }
    }
}