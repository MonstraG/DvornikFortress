package map

import digMode
import input.Help
import map.objects.Block
import map.objects.Material
import map.objects.MaterialLocale
import kotlin.random.Random

class Map(val height: Int = 256, val width: Int = 256, depth: Int = 64) {
    //todo: fix map[input.z][x][y] -> map[x][y][input.z]
    private val random = Random

    private val map = Array(height) {  Array(width) {Array(depth) { z-> Block(generateMap(z)) }}}

    var x = posX
    var y = posY
    var z = posZ

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

    private fun getVisibleWidthBounds(): Pair<Int, Int> {
        return Pair(x - Cursor.width, x + Cursor.width)
    }

    private fun getVisibleHeightBounds(): Pair<Int, Int> {
        return Pair(y - Cursor.height, y + Cursor.height)
    }

    private fun isOnCursor(x: Int, y: Int): Boolean {
        return this.x == x && this.y == y
    }

    private fun isEmpty(x: Int, y: Int): Boolean {
        return map[x][y][z].material == Material.NONE
    }

    /**
     * If block at the coordinates is empty, returns block below.
     */
    private fun getBlock(x: Int, y: Int, z: Int): Block {
        return if (isEmpty(x, y) && z > 0) map[x][y][z - 1] else map[x][y][z]
    }

    override fun toString(): String {
        val map = StringBuilder()

        val widthBounds = getVisibleWidthBounds()
        val heightBounds = getVisibleHeightBounds()

        map.append("<html style=\"background: black; color:white\"><tt>")
        for (y in heightBounds.first..heightBounds.second) {
            for (x in widthBounds.first..widthBounds.second) {
                if (x < 0 || y < 0 || x >= width || y >= height) {
                    //for positions outside map, draw empty space
                    map.append("&nbsp;")
                } else {
                    var color : String? = null
                    if (isOnCursor(x, y)) {
                        //todo color fill rectangle from digModeStart to cursor pose.
                        color = getSelectionColor()
                    }
                    appendBlock(map, x, y, z, color)
                }
            }

            //draw help
            val lineIndex = y - heightBounds.first
            if (lineIndex < Help.size){
                map.append(Help[lineIndex])
            }

            map.append("<br>")
        }
        map.append("position: [${x}, ${y}, ${z}]")
        map.append(" ${MaterialLocale[getBlock(x, y, z).material]}")
        map.append("</tt></html>")
        return map.toString()
    }

    private fun appendBlock(map: StringBuilder, x: Int, y: Int, z: Int, color: String?) {
        if (color != null) {
            map.append("<span style=\"color:" + color + "\" >${getBlock(x, y, z)}</span>")
        } else {
            map.append(getBlock(x, y, z))
        }
    }

    private fun getSelectionColor(): String? {
        if (digMode) {
            return "blue"
        }
//        if (chopMode) {
//            return "green"
//        }
        return "red"
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


