import java.awt.BorderLayout
import java.awt.Color
import java.awt.Font
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JFrame
import javax.swing.JTextPane

class GameFrame: JFrame() {
    val mapPane = MapPane()
    val input = KeyListenerImpl()
    init {
        title = "Dvornik Fortress"
        val image = ImageIO.read(File("src/resources/dvornik_fortress.png"))
        iconImage = image

        defaultCloseOperation = EXIT_ON_CLOSE
        background = Color.BLACK
        setSize(800, 400)
        setLocationRelativeTo(null)
        isVisible = true

        contentPane.add(mapPane, BorderLayout.CENTER)

        addKeyListener(input)
    }
}

class MapPane: JTextPane() {
    init {
        val font = Font(Font.MONOSPACED, Font.PLAIN, 12)
        setFont(font)
        background = Color.BLACK
        foreground = Color.LIGHT_GRAY
        isEnabled = false
        isEditable = false
    }
}

class HelpPane: JTextPane() {
    init {

    }
}