package game.orders

import game.actors.Dwarf
import game.map.Map
import game.objects.Block
import game.objects.BlockType

enum class OrderType(val hoverColor: String,val color: String) {
    NONE("red", "white"),
    DIG("blue", "#01579B"),
    BUILD( "green", "yellow")
}

/**
 * BlockType should not be assigned for DIG mode.
 */
class Order(val orderType: OrderType, val x: Int, val y: Int, val z: Int, val Block: BlockType = BlockType.NONE, var assignee: Dwarf? = null) {
    constructor(orderType: OrderType, position: Map.Position, blockType: BlockType = BlockType.NONE, assignee: Dwarf? = null) :
            this(orderType, position.x, position.y, position.z, blockType, assignee)

    fun unassigned(): Boolean {
        return assignee == null
    }

    fun dispose() {
        if (this.assignee != null) {
            this.assignee!!.assignment = null
            this.assignee = null
        }
    }
}
