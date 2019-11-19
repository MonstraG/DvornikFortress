package game.objects

import game.actors.Dwarf

class Block(val blockType: BlockType, var occupant: Dwarf? = null) {
    var hardness: Int = blockType.hardness

    fun occupied(): Boolean {
        return this.occupant != null
    }
}
