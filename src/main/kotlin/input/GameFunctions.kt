package input

import bound
import game.map.Map
import game.objects.BlockType
import gameMap
import gameState
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

fun enterBuildMode(){
    gameState.currentMode = OrderType.BUILD
}

fun leaveAnyMode() {
    gameState.currentMode = OrderType.NONE
}

fun getOrderForPos(pos: Map.Position): Order? {
    return gameState.orders.find { order -> order.x == pos.x && order.y == pos.y && order.z == pos.z }
}

fun posAlreadyInOrder(pos: Map.Position): Boolean {
    return getOrderForPos(pos) != null
}

fun addDigOrder() {
    val pos = Map.Position(gameMap.x, gameMap.y, gameMap.z)
    if (posAlreadyInOrder(pos)) {
        return
    }

    if (gameMap.isAir(pos)) {
        pos.z -= 1

        if (gameMap.isAir(pos)) {
            return
        }
    }
    gameState.orders.add(Order(OrderType.DIG, pos))
}

fun addBuildOrder(block: BlockType) {
    val pos = Map.Position(gameMap.x, gameMap.y, gameMap.z)
    if (posAlreadyInOrder(pos)) {
        return
    }
    if (!gameState.inventory.contains(block)) {
        return
    }

    gameState.orders.add(Order(OrderType.BUILD, pos, block))
}


fun pause() {
    gameState.pause()
}

fun changeGraphicsMode() {
    gameState.imageMode = !gameState.imageMode
}
