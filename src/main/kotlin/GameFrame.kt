import java.awt.BorderLayout
import java.awt.Color
import java.awt.Font
import javax.swing.JFrame
import javax.swing.JTextPane

class GameFrame: JFrame() {
    val textPane = TextPane()
    val input = KeyListenerImpl()
    init {
        title = "Dvornik Fortress"

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