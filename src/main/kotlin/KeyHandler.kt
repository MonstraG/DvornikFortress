val buttonMap = mapOf(
    'q' to {q()},
    '&' to {up_arrow()},
    '(' to {down_arrow()},
    '\'' to {right_arrow()},
    '%' to {left_arrow()}
)

fun q() {
    gameRunning = false
}

fun up_arrow() {
    gameMap.cursor.posY = (gameMap.cursor.posY - 1).coerceAtLeast(0)
}

fun down_arrow() {
    gameMap.cursor.posY = (gameMap.cursor.posY + 1).coerceAtMost(gameMap.cursor.height)
}

fun right_arrow() {
    gameMap.cursor.posX = (gameMap.cursor.posX + 1).coerceAtLeast(0)
}

fun left_arrow() {
    gameMap.cursor.posX = (gameMap.cursor.posX - 1).coerceAtMost(gameMap.cursor.width)
}