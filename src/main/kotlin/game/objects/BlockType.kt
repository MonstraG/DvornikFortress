package game.objects

enum class BlockType(val mapChar: String, val img: String,  val locale: String, val hardness: Int) {
    NONE("&nbsp;", "", "Air", 0),
    DIRT("'","<img src=\"src/resources/grass.png\">", "Dirt", 25),
    STONE("#", "", "Stone", 100),
    WOOD("O", "<img src=\"src/resources/tree.png\">", "Wood", 50),
    GOLD("G", "", "Gold", 85),
    IRON("0", "", "Iron", 85),
    BEDROCK("░", "", "Bedrock", 999999999)
}
