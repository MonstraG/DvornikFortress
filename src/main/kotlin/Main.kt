import javafx.application.Platform
import java.awt.Color
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
            Platform.runLater { drawMap() }
            sleep(40) //25 fps
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
                if (x < 0 || y < 0 || x >= gameMap.width || y >= gameMap.height) {
                    //for positions outside map, draw empty space
                    map.append("&nbsp;")
                } else {
                    var color : String? = null
                    if (gameMap.isOnCursor(x, y)) {
                        color = "red"
                    }
                    drawBlock(map, x, y, gameMap.z, color)
                }
            }

            //draw help
            val lineIndex = y - heightBounds.first
            if (lineIndex < Help.size){
                map.append(Help[lineIndex])
            }

            map.append("<br>")
        }
        map.append("position: [${gameMap.x}, ${gameMap.y}, ${gameMap.z}]")
        map.append(" ${MaterialLocale[gameMap.getBlock(gameMap.x, gameMap.y, gameMap.z).material]}")
        map.append("</tt></html>")
        gameFrame.browser.engine.loadContent(map.toString())
    }

    private fun drawBlock(map: StringBuilder, x: Int, y: Int, z: Int, color: String?) {
        if (color != null) {
            map.append("<span style=\"color:" + color + "\" >${gameMap.getBlock(x, y, z)}</span>")
        } else {
            map.append(gameMap.getBlock(x, y, z))
        }
    }
}
