package game.actors


//todo place dwarfs
class Dwarf(var x: Int, var y: Int, var z: Int) {
    val DWARF_SPEED = 5

    override fun toString(): String {
        return "@"
    }
}