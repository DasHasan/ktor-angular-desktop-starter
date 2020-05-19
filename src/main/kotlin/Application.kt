import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.*
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.defaultResource
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.jackson.jackson
import io.ktor.response.respondRedirect
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.awt.Desktop
import java.net.URI
import java.util.*
import kotlin.concurrent.schedule

class Application

fun main(args: Array<String>) {
    systemTray {
        trayIcon("tray_icon.png", "Studip Crawler") {
            click {
                Desktop.getDesktop().browse(URI("http://localhost:8080/"))
            }
            menu {
                item("Open") {
                    Desktop.getDesktop().browse(URI("http://localhost:8080/"))
                }
                separator()
                item("Close") {
                    System.exit(0)
                }
            }
        }
    }

    startServer()
}

private fun startServer() {
    embeddedServer(Netty, port = 6441, module = {
        install(DefaultHeaders)
        install(CallLogging)
        install(CORS) {
            host("localhost:4200")
        }
        install(StatusPages) {
            status(HttpStatusCode.NotFound) {
                call.respondRedirect("/")
            }
        }
        routing {
            api()
            static("") {
                resources("/frontend")
                defaultResource("/frontend/index.html")
            }
        }
        install(ContentNegotiation) {
            jackson {
                // Configure Jackson's ObjectMapper here
            }
        }
    }).start(wait = true)
}
