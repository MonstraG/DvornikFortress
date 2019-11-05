package game.actors

import game.map.Map
import game.orders.Order
import gameMap

class Dwarf(var x: Int, var y: Int, var z: Int, val name: String = "Dwarf") {
    object DwarfConstants {
        const val DWARF_SPEED = 5
    }

    val mapChar = "@"

    var assignment: Order? = null

    fun unassigned(): Boolean {
        return assignment == null
    }

    private val moves = mutableListOf<Map.Position>()

    fun move() {
        if (moves.isNotEmpty()) {
            val pos = moves[0]
            val nextBlock = gameMap.getBlock(pos)
            if (nextBlock.occupied() || !gameMap.canBeOccupied(pos.x, pos.y, pos.z)) {
                calculatePath()
            } else {
                gameMap.getBlock(Map.Position(this.x, this.y, this.z)).occupant = null
                nextBlock.occupant = this
            }
        }
    }

    private fun calculatePath() {
        //todo will add moves to moves list
    }
}