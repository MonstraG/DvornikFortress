package game.objects

enum class BlockType(val mapChar: String, val locale: String) {
    NONE("&nbsp;", "Air"),
    DIRT("'", "Dirt"),
    STONE("#", "Stone"),
    WOOD("O", "Wood"),
    GOLD("G", "Gold"),
    IRON("0", "Iron"),
    BEDROCK("░", "Bedrock")
}
