package game.objects

import game.actors.Dwarf

class Block(val blockType: BlockType, var occupant: Dwarf? = null) {
    var hardness: Int = blockType.hardness

    fun occupied(): Boolean {
        return this.occupant != null
    }

    fun getDisplayChar(): String {
        return if (this.occupied()) {
            this.occupant!!.mapChar
        } else {
            this.blockType.mapChar
        }
    }

    fun getDisplayImg(): String {
        return if (this.occupied()) {
            this.occupant!!.img
        } else {
            this.blockType.img
        }
    }

    fun getDisplayLocale(): String {
        return if (this.occupied()) {
            this.occupant!!.name
        } else {
            this.blockType.locale
        }
    }
}
