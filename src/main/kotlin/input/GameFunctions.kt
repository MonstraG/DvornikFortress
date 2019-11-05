package input

import bound
import gameMap
import gameState
import game.map.Map
import game.orders.Order
import game.orders.OrderType

fun exit() {
    gameState.gameRunning = false
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
    gameState.currentMode = OrderType.DIG
}

fun cancelMode() {
    gameState.currentMode = OrderType.NONE
}

fun addDigOrder() {
    gameState.orders.add(Order(OrderType.DIG, gameMap.x, gameMap.y, gameMap.z))
}

fun pause() {
    gameState.pause()
}