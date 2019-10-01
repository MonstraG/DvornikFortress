package com.arseniy

enum class Material {
    NONE,
    DIRT,
    STONE
}

class Block {
    var type = Material.NONE

    // todo: var occupant
}

class Map() {
    //2D array of Tiles
    val map = Array(256) {Array(256) { Array(64) {Block()} } }

}