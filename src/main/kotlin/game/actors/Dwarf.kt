package game.actors

import game.actors.Dwarf.DwarfConstants.STRENGTH
import game.map.Map
import game.map.Map.Util.getNear
import game.objects.Block
import game.objects.BlockType
import game.orders.Order
import game.orders.OrderType
import gameMap
import gameState

class Dwarf(var x: Int, var y: Int, var z: Int, val name: String = "Dwarf") {
    object DwarfConstants {
        const val DWARF_SPEED = 5
        const val STRENGTH = 5
    }

    val mapChar = "@"
    val img = "src/resources/dwarf.png"

    var assignment: Order? = null
    fun unassigned(): Boolean {
        return assignment == null
    }

    private var moves = mutableListOf<Map.Position>()

    fun act() {
        if (moves.isNotEmpty()) {
            val pos = moves.removeAt(0)
            val nextBlock = gameMap.getBlock(pos)
            if (nextBlock.occupied() || !gameMap.canBeOccupied(pos.x, pos.y, pos.z)) {
                makePath()
            } else {
                gameMap.getBlock(Map.Position(this.x, this.y, this.z)).occupant = null
                nextBlock.occupant = this
                this.x = pos.x
                this.y = pos.y
                this.z = pos.z
            }
        } else {
            makePath()
        }

        if (assignment != null && moves.isEmpty()) {
            doOrder()
        }
    }

    private fun doOrder() {
        assignment?.let{assignment->
            when (assignment.orderType) {
                OrderType.DIG -> dig(Map.Position(assignment.x, assignment.y, assignment.z))
                OrderType.BUILD -> build(Map.Position(assignment.x, assignment.y, assignment.z),assignment.Block)
                else -> return
            }
        }

    }

    private fun dig(position: Map.Position) {
        var block = gameMap.getBlock(position)
        if (block.blockType == BlockType.NONE){
            position.z = position.z-1
            block = gameMap.getBlock(position)
        }
        if (block.hardness > 0) {
            block.hardness -= STRENGTH
        } else {
            gameMap.setBlock(position, Block(BlockType.NONE, null))
            gameState.addToInventory(block.blockType)
            gameState.completeOrder(assignment!!)
        }
    }

    fun build(position: Map.Position, blockType: BlockType) {
        var block = gameMap.getBlock(position)
        if (block.blockType != BlockType.NONE){
            position.z += 1
            block = gameMap.getBlock(position)
        }
        gameMap.setBlock(position, Block(blockType))
        gameState.completeOrder(assignment!!)
    }

    private fun makePath() {
        if (assignment != null) {
            val path = calculatePath()
            if (path != null) {
                println("Calculated path for dwarf ${this.name}, length = ${path.size}")
                this.moves = path
            }
        }
    }

    private fun calculatePath(): MutableList<Map.Position>? {
        val goal = Map.Position(assignment!!.x, assignment!!.y, assignment!!.z)
        val start = Map.Position(this.x, this.y, this.z)

        val queue = mutableListOf(start)
        val pathStack = mutableListOf<Map.Position>()
        val visited = mutableListOf(start)
        var added = true
        while (queue.isNotEmpty()) {
            val current = queue.removeAt(0)

            if (!added) {
                while (pathStack.isNotEmpty()) {
                    val nextPath = pathStack.removeAt(pathStack.lastIndex)
                    if (getNear(nextPath).contains(current)) {
                        break
                    }
                }
                continue
            }

            pathStack.add(current)
            if (Map.Util.calculateDistance(current, goal) <= 1) {
                return pathStack.subList(1, pathStack.size)
            }

            added = false
            getNear(current).forEach {
                if (!visited.contains(it)) {
                    queue.add(it)
                    visited.add(it)
                    added = true
                }
            }

            queue.sortBy { Map.Util.calculateDistance(it, goal) }
        }
        return pathStack
    }
}