package game.objects

enum class BlockType(val mapChar: String, val locale: String, val hardness: Int) {
    NONE("&nbsp;", "Air", 0),
    DIRT("'", "Dirt", 25),
    STONE("#", "Stone", 100),
    WOOD("O", "Wood", 50),
    GOLD("G", "Gold", 85),
    IRON("0", "Iron", 85),
    BEDROCK("░", "Bedrock", 999999999)
}
