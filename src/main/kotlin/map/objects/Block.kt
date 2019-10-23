package map.objects

class Block(val blockType: BlockType) {

    override fun toString(): String {
        return blockType.mapChar
    }
}
