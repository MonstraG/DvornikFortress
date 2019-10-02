enum class Material {
    NONE,
    DIRT,
    STONE
}

class Block(val material: Material = Material.NONE) {
}

class Map() {
    val map = Array(256) {Array(256) { Block(Material.DIRT) }}
}
