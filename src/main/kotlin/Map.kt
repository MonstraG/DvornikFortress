import kotlin.random.Random
const val DIRT_HEIGHT = 48

enum class Material {
    NONE,
    DIRT,
    STONE,
    WOOD,
    GOLD,
    IRON,
    BEDROCK
}

val MaterialLocale = mapOf(
    Material.DIRT to "Dirt",
    Material.IRON to "Iron",
    Material.GOLD to "Gold",
    Material.STONE to "Stone",
    Material.WOOD to "Wood",
    Material.BEDROCK to "Bedrock",
    Material.NONE to "Air")

val MaterialToCharMap = mapOf(
    Material.NONE to "&nbsp;",
    Material.DIRT to '\'',
    Material.STONE to '#',
    Material.WOOD to 'O',
    Material.GOLD to 'G',
    Material.IRON to '0',
    Material.BEDROCK to '░'
)

class Block(val material: Material = Material.NONE) {
    
    override fun toString(): String {
        return MaterialToCharMap[this.material].toString()
    }
}

private val random = Random

class Map(val height: Int = 256, val width: Int = 256, depth: Int = 64) {
    private val cursor = Cursor()
    //todo: fix map[z][x][y] -> map[x][y][z]
    val map = Array(depth) { z-> Array(height) {Array(width) { Block(generateMap(z)) }}}

    private fun generateMap(z: Int): Material {
        return when {
            z > DIRT_HEIGHT + 1 -> Material.NONE
            z == DIRT_HEIGHT + 1 -> {
                return if (random.nextFloat() < 0.05) Material.WOOD else Material.NONE
            }
            z == DIRT_HEIGHT -> Material.DIRT
            z == 0 -> Material.BEDROCK
            else -> {
                val rnd = random.nextFloat()
                when {
                    rnd < 0.02 -> Material.GOLD
                    rnd < 0.05 -> Material.IRON
                    else -> Material.STONE
                }
            }
        }
    }

    var x = cursor.posX
    var y = cursor.posY
    var z = cursor.posZ

    fun getVisibleWidthBounds(): Pair<Int, Int> {
        return Pair(x - cursor.width, x + cursor.width)
    }

    fun getVisibleHeightBounds(): Pair<Int, Int> {
        return Pair(y - cursor.height, y + cursor.height)
    }

    fun isOnCursor(x: Int, y: Int): Boolean {
        return this.x == x && this.y == y
    }

    fun isEmpty(x: Int, y: Int): Boolean {
        return map[this.z][x][y].material == Material.NONE
    }

    /**
     * If block at the coords is empty, returns block below.
     */
    fun getBlock(x: Int, y: Int, z: Int): Block {
        return if (isEmpty(x, y) && z > 0) map[z - 1][x][y] else map[z][x][y]
    }
}

class Cursor {
    //current position of the screen center
    var posX = 128
    var posY = 128
    var posZ = DIRT_HEIGHT + 1

    //we draw $width$ blocks to the left of the cursor, and same amount to the right
    var width = 30
    var height = 12
}
