import java.awt.BorderLayout
import java.awt.Color
import java.awt.Font
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JFrame
import javax.swing.JTextPane

class GameFrame: JFrame() {
    val textPane = TextPane()
    val input = KeyListenerImpl()
    init {
        title = "Dvornik Fortress"
        val image = ImageIO.read(File("src/resources/dvornik_fortress.png"))
        iconImage = image

        defaultCloseOperation = EXIT_ON_CLOSE
        background = Color.BLACK
        textPane.background = Color.BLACK
        setSize(800, 400)
        setLocationRelativeTo(null)
        isVisible = true

        contentPane.add(textPane, BorderLayout.CENTER)

        addKeyListener(input)
    }
}

class TextPane: JTextPane() {
    init {
        val font = Font("Courier New", Font.PLAIN, 12)
        setFont(font)
        foreground = Color.LIGHT_GRAY
        isEnabled = false
        isEditable = false
    }
}