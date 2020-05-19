import io.ktor.application.call
import io.ktor.application.install
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.api() {
    route("api"){

        get("system") {
            this.call.respond(SystemInfo())
        }

    }
}

data class SystemInfo(
    val osName: String = System.getProperty("os.name"),
    val javaHome: String = System.getProperty("java.home"),
    val userName: String = System.getProperty("user.name"),
    val totalMemory: Long = Runtime.getRuntime().totalMemory(),
    val freeMemory: Long = Runtime.getRuntime().freeMemory()
)
