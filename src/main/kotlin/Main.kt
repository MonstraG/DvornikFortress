import javafx.application.Platform
import map.Map

//todo: move out to gameState object, that is initialized in run()
var gameRunning = true
var digMode = false
var digModeStartPos = Triple(0, 0, 0)

val gameMap = Map()
val gameFrame = GameFrame()

fun main() {
    Game.start()
}

object Game: Thread() {
    override fun run() {
        while (gameRunning) {
            Platform.runLater {  gameFrame.browser.engine.loadContent(gameMap.toString()) }
            sleep(40) //25 fps
        }
        gameFrame.dispose()
    }
}
