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
    val cursor = Cursor()
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

    fun getVisibleWidthBounds(): Pair<Int, Int> {
        return Pair(
            cursor.posX - cursor.width,
            cursor.posX + cursor.width
        )
    }

    fun getVisibleHeightBounds(): Pair<Int, Int> {
        return Pair(
            cursor.posY - cursor.height,
            cursor.posY + cursor.height
        )
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
