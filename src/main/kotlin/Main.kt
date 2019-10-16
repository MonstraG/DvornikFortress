import javafx.application.Platform
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
            Platform.runLater {
                drawMap()
            }
            sleep(40)
        }
        gameFrame.dispose()
    }

    private fun drawMap() {
        val map = StringBuilder()

        val widthBounds = gameMap.getVisibleWidthBounds()
        val heightBounds = gameMap.getVisibleHeightBounds()

        map.append("<html style=\"background: black; color:white\"><tt>")
        for (y in heightBounds.first..heightBounds.second) {
            for (x in widthBounds.first..widthBounds.second) {
                //todo: fix right corner
                if (x < 0 || y < 0 || x >= gameMap.width || y >= gameMap.height) {
                    map.append("&nbsp;")
                } else {
                    if (x == gameMap.cursor.posX && y == gameMap.cursor.posY){
                        map.append("<span style=\"color:red\" >${gameMap.map[gameMap.cursor.posZ][x][y]}</span>")
                    }
                    else {
                        map.append(gameMap.map[gameMap.cursor.posZ][x][y])
                    }
                }
            }
            val lineIndex = y - heightBounds.first
            if (lineIndex < Help.size){
                map.append(Help[lineIndex])
            }
            map.append("<br>")
        }
        map.append("</tt></html>")
        gameFrame.browser.engine.loadContent(map.toString())
    }
}
