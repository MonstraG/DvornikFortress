package game.map

import game.orders.OrderType
import gameMap
import gameState
import input.Interface
import java.lang.StringBuilder

class MapSerializer {
    private val mapStringBuilder = StringBuilder()

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
            gameState.getOrderForBlock(x, y, z)?.color ?: OrderType.NONE.color
        }
        mapStringBuilder.append("<span style=\"color:" + color + "\" >${gameMap.getOccupantOrBlockChar(x, y, z)}</span>")
        return this
    }

    fun appendInterface(lineIndex: Int): MapSerializer {
        if (lineIndex < Interface.size){
            mapStringBuilder.append(Interface[lineIndex])
        }
        return this
    }


    fun appendCursorInfo(x: Int, y: Int, z: Int): MapSerializer {
        mapStringBuilder.append("position: [$x, $y, $z]")
        mapStringBuilder.append("&emsp;&emsp;${gameMap.getOccupantOrBlockLocale(x, y, z)}")
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