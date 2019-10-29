package game.map

import game.objects.Block
import game.objects.BlockType
import kotlin.random.Random

class Map(val height: Int = 256, val width: Int = 256, depth: Int = 64) {
    private val random = Random
    private val serializer = MapSerializer()

    private val map = Array(height) { Array(width) { Array(depth) { z-> Block(generateMap(z)) }}}

    var x = posX
    var y = posY
    var z = posZ

    private fun generateMap(z: Int): BlockType {
        return when {
            z > DIRT_HEIGHT + 1 -> BlockType.NONE
            z == DIRT_HEIGHT + 1 -> {
                return if (random.nextFloat() < 0.05) BlockType.WOOD else BlockType.NONE
            }
            z == DIRT_HEIGHT -> BlockType.DIRT
            z == 0 -> BlockType.BEDROCK
            // DIRT_HEIGHT > z > 0
            else -> {
                val rnd = random.nextFloat()
                when {
                    rnd < 0.02 -> BlockType.GOLD
                    rnd < 0.05 -> BlockType.IRON
                    else -> BlockType.STONE
                }
            }
        }
    }

    private fun getVisibleWidthBounds(): Pair<Int, Int> {
        return Pair(x - Cursor.width, x + Cursor.width)
    }

    private fun getVisibleHeightBounds(): Pair<Int, Int> {
        return Pair(y - Cursor.height, y + Cursor.height)
    }

    fun isOnCursor(x: Int, y: Int): Boolean {
        return this.x == x && this.y == y
    }

    private fun isEmpty(x: Int, y: Int): Boolean {
        return map[x][y][z].blockType == BlockType.NONE
    }

    /**
     * If block at the coordinates is empty, returns block below.
     */
    fun getBlock(x: Int, y: Int, z: Int): Block {
        return if (isEmpty(x, y) && z > 0) map[x][y][z - 1] else map[x][y][z]
    }

    override fun toString(): String {
        val widthBounds = getVisibleWidthBounds()
        val heightBounds = getVisibleHeightBounds()
        for (y in heightBounds.first..heightBounds.second) {
            for (x in widthBounds.first..widthBounds.second) {
                if (x < 0 || y < 0 || x >= width || y >= height) {
                    //for positions outside map, draw empty space
                    serializer.appendEmpty()
                } else {
                    serializer.appendBlock(x, y, z)
                }
            }
            serializer.appendHelp(y - heightBounds.first).appendNewLine()
        }
        return serializer.appendCursorInfo(x, y, z).finalize()
    }

    companion object Cursor {
        const val DIRT_HEIGHT = 48

        //current position of the screen center
        var posX = 128
        var posY = 128
        var posZ = DIRT_HEIGHT + 1

        //we draw $width$ blocks to the left of the cursor, and same amount to the right
        var width = 30
        var height = 12
    }
}


