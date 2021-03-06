package game.map

import game.displayInventory
import game.getHelpLines
import game.orders.OrderType
import gameMap
import gameState
import java.io.File
import java.lang.StringBuilder

class MapSerializer {
    private val mapStringBuilder = StringBuilder()
    private var inventoryDisplay: Array<String>? = null

    fun appendEmpty(): MapSerializer {
        mapStringBuilder.append("&nbsp;")
        return this
    }

    fun appendNewLine(): MapSerializer {
        mapStringBuilder.append("<br>")
        return this
    }

    fun appendBlock(x: Int, y: Int, z: Int): MapSerializer {
        val color = if (gameMap.isOnCursor(x, y)) {
            gameState.currentMode.hoverColor
        } else {
            //get color of order, if null then default color.
            gameState.getOrderForBlockOrBelow(x, y, z)?.color ?: OrderType.NONE.color
        }

        val opacity: Double = if (gameMap.isEmpty(x, y, z)) 0.75 else 1.0
        if (gameState.imageMode) {
            mapStringBuilder.append("<span style=\"opacity: $opacity;\"><img src=\"${File(gameMap.getOccupantOrBlockImg(x, y, z)).toURI()}</span>")
        } else {
            mapStringBuilder.append("<span style=\"color: $color; opacity: $opacity;\">${gameMap.getOccupantOrBlockChar(x, y, z)}</span>")
        }
        return this
    }

    fun appendHelpLine(lineIndex: Int): MapSerializer {
        val help = getHelpLines()
        if (lineIndex < help.size){
            mapStringBuilder.append(help[lineIndex])
        }
        return this
    }

    fun appendInventoryLine(lineIndex: Int): MapSerializer {
        val help = getHelpLines()
        if (inventoryDisplay == null) {
            inventoryDisplay = displayInventory()
        }
        if (lineIndex - help.size < inventoryDisplay!!.size && lineIndex >= help.size) {
            mapStringBuilder.append(inventoryDisplay!![lineIndex - help.size])
        } else {
            inventoryDisplay = null
        }
        return this
    }

    fun appendCursorInfo(x: Int, y: Int, z: Int): MapSerializer {
        val prefix = if (gameMap.isEmpty(x, y, z)) {
            mapStringBuilder.append("position: [$x, $y, ${z-1}]")
            "below: "
        } else {
            mapStringBuilder.append("position: [$x, $y, ${z}]")
            ""
        }
        mapStringBuilder.append("&emsp;&emsp;${prefix}${gameMap.getOccupantOrBlockLocale(x, y, z)}")
        mapStringBuilder.append("&emsp;&emsp;time: ${gameState.tick}")
        return this
    }

    fun finalize(): String {
        mapStringBuilder.append("</tt></html>")
        try {
            return mapStringBuilder.toString()
        } finally {
            mapStringBuilder.clear()
            mapStringBuilder.append("<html style=\"background: black; color:white\"><tt>")
        }
    }
}