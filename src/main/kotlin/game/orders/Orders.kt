package game.orders

import game.actors.Dwarf

enum class OrderType(val hoverColor: String,val color: String) {
    NONE("red", "white"),
    DIG("blue", "#01579B")
}

class Order(val orderType: OrderType, val x: Int, val y: Int, val z: Int, var assignee: Dwarf? = null) {
    fun unassigned(): Boolean {
        return assignee == null
    }
}
