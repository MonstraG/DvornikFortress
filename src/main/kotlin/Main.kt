import java.lang.StringBuilder

var gameRunning = true
val gameMap = Map()
var gameFrame = GameFrame()

fun main() {
    Game.start()
}

object Game: Thread() {
    override fun run() {
        while (gameRunning) {
            if (gameFrame.input.queue.isNotEmpty()) {
                val buttonCode = gameFrame.input.queue.remove()
                val button = buttonCode.toChar().toLowerCase()
                val buttonCodeLower = button.toInt()
                if (button == 'q')
                    gameRunning = false

                println("$button ($buttonCode, lower: $buttonCodeLower)")
            }
            drawMap()
            sleep(16)
        }
        gameFrame.dispose()
    }

    fun drawMap() {
        val map = StringBuilder()

        val widthBounds = gameMap.getVisibleWidthBounds()
        val heightBounds = gameMap.getVisibleHeightBounds()
        for (y in heightBounds.first..heightBounds.second) {
            for (x in widthBounds.first..widthBounds.second) {
                map.append(gameMap.map[x][y])
            }
            map.append("\n")
        }
        gameFrame.textPane.text = map.toString()
    }
}