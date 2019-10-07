enum class Material {
    NONE,
    DIRT,
    STONE
}

//map to char instead of string to ensure that every material has 1-char representation.
val MaterialToCharMap = mapOf(
    Material.NONE to ' ',
    Material.DIRT to '\'',
    Material.STONE to '#'
)

class Block(val material: Material = Material.NONE) {
    
    override fun toString(): String {
        return MaterialToCharMap[this.material].toString()
    }
}

class Map(val height: Int = 256, val width: Int = 256) {
    val map = Array(height) {Array(width) { Block(Material.DIRT) }}
    val cursor = Cursor()

    fun getVisibleWidthBounds(): Pair<Int, Int> {
        return Pair(
            (cursor.posX - cursor.width).coerceAtLeast(0),
            (cursor.posX + cursor.width).coerceAtMost(width)
        )
    }

    fun getVisibleHeightBounds(): Pair<Int, Int> {
        return Pair(
            (cursor.posY - cursor.height).coerceAtLeast(0),
            (cursor.posY + cursor.height).coerceAtMost(height)
        )
    }
}

class Cursor {
    //current position of the screen center
    var posX = 128
    var posY = 128
    //var posZ = 32; //will be added later

    //we draw %width blocks to the left of the cursor, and same amount to the right
    var width = 30
    var height = 12
}
