val buttonMap = mapOf(
    81 to {q()},
    38 to {upArrow()},
    40 to {downArrow()},
    39 to {rightArrow()},
    37 to {leftArrow()},
    //32 to {changeLevel()},
    85 to {levelUp()},
    68 to {levelDown()}
)

fun q() {
    gameRunning = false
}

fun levelUp(){
    gameMap.cursor.posZ = (gameMap.cursor.posZ + 1) % 64
}

fun levelDown(){
    gameMap.cursor.posZ = (gameMap.cursor.posZ - 1)
}
/*

fun changeLevel(){
    if (gameMap.material == Material.DIRT) {
        gameMap.map = Array(256) { Array(256) { Block(Material.STONE) } }
        gameMap.material = Material.STONE
    }
    else {
        gameMap.map = Array(256) { Array(256) { Block(Material.DIRT) } }
        gameMap.material = Material.DIRT
    }
}
*/

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