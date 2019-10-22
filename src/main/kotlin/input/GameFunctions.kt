package input

import bound
import digMode
import digModeStartPos
import gameMap
import gameRunning

fun exit() {
    gameRunning = false
}

fun riseUp() {
    gameMap.z = bound(gameMap.z + 1, 64)
}

fun goDown() {
    gameMap.z = bound(gameMap.z - 1, 64)
}

fun moveUp() {
    gameMap.y = bound(gameMap.y - 1, gameMap.height)
}

fun moveDown() {
    gameMap.y = bound(gameMap.y + 1, gameMap.height)
}

fun moveLeft() {
    gameMap.x = bound(gameMap.x - 1, gameMap.width)
}

fun moveRight() {
    gameMap.x = bound(gameMap.x + 1, gameMap.width)
}

fun enterDigMode() {
    digMode = true
    digModeStartPos = Triple(gameMap.x, gameMap.y, gameMap.z)
}

fun finalizeDigOrder() {
    digMode = false
    //todo
}