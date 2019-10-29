import javafx.application.Platform
import game.map.Map

val gameMap = Map()
val gameFrame = GameFrame()
val gameState = GameState()

fun main() {
    Game.start()
}

object Game: Thread() {
    override fun run() {
        while (gameState.gameRunning) {
            Platform.runLater {  gameFrame.browser.engine.loadContent(gameMap.toString()) }
            sleep(40) //25 fps
        }
        gameFrame.dispose()
    }
}
