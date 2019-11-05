package game.objects

import game.actors.Dwarf

class Block(val blockType: BlockType, var occupant: Dwarf? = null) {
    fun occupied(): Boolean {
        return this.occupant != null
    }
}
