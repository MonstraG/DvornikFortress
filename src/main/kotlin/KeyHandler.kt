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
    gameMap.cursor.posY = (gameMap.cursor.posY - 1).coerceAtLeast(0).coerceAtMost(gameMap.height)
}

fun downArrow() {
    gameMap.cursor.posY = (gameMap.cursor.posY + 1).coerceAtLeast(0).coerceAtMost(gameMap.height)
}

fun rightArrow() {
    gameMap.cursor.posX = (gameMap.cursor.posX + 1).coerceAtLeast(0).coerceAtMost(gameMap.width)
}

fun leftArrow() {
    gameMap.cursor.posX = (gameMap.cursor.posX - 1).coerceAtLeast(0).coerceAtMost(gameMap.width)
}