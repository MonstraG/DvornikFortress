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
                buttonMap[button]?.invoke()

                println("$button ($buttonCode, lower: $buttonCodeLower)")
                println("${gameMap.cursor.posX}, ${gameMap.cursor.posY}\n")
            }
            drawMap()
            sleep(16)
        }
        gameFrame.dispose()
    }

    private fun drawMap() {
        val map = StringBuilder()

        val widthBounds = gameMap.getVisibleWidthBounds()
        val heightBounds = gameMap.getVisibleHeightBounds()

        for (y in heightBounds.first..heightBounds.second) {
            for (x in widthBounds.first..widthBounds.second) {
                if (x < 0 || y < 0 || x > gameMap.width || y > gameMap.height) {
                    map.append(' ')
                } else {
                    map.append(gameMap.map[x][y])
                }
            }
            map.append("\n")
        }
        gameFrame.mapPane.text = map.toString()

    }
}