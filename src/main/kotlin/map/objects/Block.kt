package map.objects

class Block(val material: Material = Material.NONE) {

    override fun toString(): String {
        return MaterialToCharMap[this.material].toString()
    }
}
