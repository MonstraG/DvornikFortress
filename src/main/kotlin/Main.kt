import java.util.concurrent.ConcurrentLinkedQueue

var gameRunning = true
val gameMap = Map()

fun main(args: Array<String>) {
    Input.start()
    Game.start()
    Game.join()
}

object Input: Thread() {
    val queue = ConcurrentLinkedQueue<Int>()

    override fun run() {
        while (gameRunning) {
            queue.add(RawConsoleInput.read(true))
        }

    }
}

object Game: Thread() {
    override fun run() {
        while (gameRunning)
            if (Input.queue.isNotEmpty()) {
                val buttonCode = Input.queue.remove()
                val button = buttonCode.toChar().toLowerCase()
                val buttonCodeLower = button.toInt()
                if (button == 'q')
                    gameRunning = false

                println("$button ($buttonCode, lower: $buttonCodeLower)")
            }
    }

    fun drawMap() {
        //todo
        println(gameMap.map)
    }
}