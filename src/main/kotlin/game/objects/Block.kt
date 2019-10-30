package game.objects

import game.actors.Dwarf

class Block(val blockType: BlockType, var occupant: Dwarf? = null) {

    override fun toString(): String {
        if (occupant != null) {
            return occupant.toString()
        }
        return blockType.mapChar
    }
}
