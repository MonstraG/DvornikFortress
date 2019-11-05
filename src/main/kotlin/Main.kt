import javafx.application.Platform
import game.map.Map

val gameState = GameState()
val gameFrame = GameFrame()
val gameMap = Map()

const val MS_IN_TICK = 40L  //25 fps

fun main() {
    while (gameState.gameRunning) {
        gameState.tick()
        Platform.runLater {  gameFrame.browser.engine.loadContent(gameMap.toString()) }
        Thread.sleep(MS_IN_TICK)
    }
    gameFrame.dispose()
}