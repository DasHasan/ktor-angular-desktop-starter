import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.ImageIcon

fun systemTray(init: SystemTray.() -> Unit) {
    if (SystemTray.isSupported()) {
        SystemTray.getSystemTray().apply(init)
    }
}

fun SystemTray.trayIcon(image: String, tooltip: String, init: TrayIcon.() -> Unit) {
    val path = Application::class.java.getResource("/$image")?.path
    val trayIcon = TrayIcon(ImageIcon(path).image, tooltip)
    trayIcon.init()
    add(trayIcon)
}

fun TrayIcon.menu(init: PopupMenu.() -> Unit) {
    val menu = PopupMenu()
    menu.init()
    popupMenu = menu
}

fun TrayIcon.click(handler: () -> Unit) {
    addMouseListener(object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent?) {
            if (e?.button == 1) {
                handler()
            }
        }
    })
}

fun PopupMenu.item(label: String, handler: () -> Unit) {
    val menuItem = MenuItem(label)
    menuItem.addActionListener {
        handler()
    }
    add(menuItem)
}

fun PopupMenu.separator() {
    addSeparator()
}