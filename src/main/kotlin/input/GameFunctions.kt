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
    gameMap.z = (gameMap.z + 1).bound(0, 64)
}

fun goDown() {
    gameMap.z = (gameMap.z - 1).bound(0, 64)
}

fun moveUp() {
    gameMap.y = (gameMap.y - 1).bound(0, gameMap.height)
}

fun moveDown() {
    gameMap.y = (gameMap.y + 1).bound(0, gameMap.height)
}

fun moveLeft() {
    gameMap.x = (gameMap.x - 1).bound(0, gameMap.width)
}

fun moveRight() {
    gameMap.x = (gameMap.x + 1).bound(0, gameMap.width)
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
        if (gameMap.isAir(gameMap.x, gameMap.y, gameMap.z - 1)) {
            return
        } else {
            gameState.orders.add(Order(OrderType.DIG, gameMap.x, gameMap.y, gameMap.z - 1))
            return
        }
    }
    gameState.orders.add(Order(OrderType.DIG, gameMap.x, gameMap.y, gameMap.z))
}

fun addBuildOrder(block: BlockType) {
    val pos = Map.Position(gameMap.x, gameMap.y, gameMap.z)
    if (posAlreadyInOrder(pos)) {
        return
    }

    if (!gameState.inventory.has(block)) {
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

fun moveUpInInventory() {
    if (gameState.currentMode == OrderType.BUILD) {
        gameState.inventory.moveSelectedUp()
    }
}

fun moveDownInInventory() {
    if (gameState.currentMode == OrderType.BUILD) {
        gameState.inventory.moveSelectedDown()
    }
}