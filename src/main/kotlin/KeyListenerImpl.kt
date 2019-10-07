import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.util.concurrent.ConcurrentLinkedQueue

class KeyListenerImpl: KeyListener {
    val queue = ConcurrentLinkedQueue<Int>()

    override fun keyPressed(e: KeyEvent?) {
        queue.add(e!!.keyCode)
    }

    override fun keyReleased(p0: KeyEvent?) {
        //not needed
    }

    override fun keyTyped(e: KeyEvent?) {
        //not needed
    }
}