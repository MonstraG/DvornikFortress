enum class Material {
    NONE,
    DIRT,
    STONE
}

class Block(val material: Material = Material.NONE) {
    
    override fun toString(): String {
        //replace "#" with actual Material -> String map later
        return "#"
    }
}

class Map() {
    val map = Array(256) {Array(256) { Block(Material.DIRT) }}
}

class Cursor() {
    //current position of the screen center
    var posX = 128;
    var posY = 128;
    //var posZ = 32; //will be added later
    
    //we draw %width blocks to the left of the cursor, and same amount to the rught
    var width = 30;
    var height = 12;
}
