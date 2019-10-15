val buttonMap = mapOf(
    81 to {q()},
    38 to {upArrow()},
    40 to {downArrow()},
    39 to {rightArrow()},
    37 to {leftArrow()}
)

fun q() {
    gameRunning = false
}

fun upArrow() {
    gameMap.cursor.posY = bound(gameMap.cursor.posY - 1, gameMap.height)
}

fun downArrow() {
    gameMap.cursor.posY = bound(gameMap.cursor.posY + 1, gameMap.height)
}

fun rightArrow() {
    gameMap.cursor.posX = bound(gameMap.cursor.posX + 1, gameMap.width)
}

fun leftArrow() {
    gameMap.cursor.posX = bound(gameMap.cursor.posX - 1, gameMap.width)
}

fun bound(value: Int, max: Int): Int {
    return value.coerceAtLeast(0).coerceAtMost(max - 1)
}