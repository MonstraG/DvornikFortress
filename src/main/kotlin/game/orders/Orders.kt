package game.orders

import game.actors.Dwarf
import game.objects.Block
import game.objects.BlockType

enum class OrderType(val hoverColor: String,val color: String) {
    NONE("red", "white"),
    DIG("blue", "#01579B"),
    BUILD( "green", "yellow")
}

class Order(val orderType: OrderType, val x: Int, val y: Int, val z: Int, var Block: BlockType, var assignee: Dwarf? = null) {
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
