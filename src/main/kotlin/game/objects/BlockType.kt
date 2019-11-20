package game.objects

enum class BlockType(val mapChar: String, val locale: String, val hardness: Int) {
    NONE("&nbsp;", "Air", 0),
    DIRT("<img src=\"https://iconizer.net/files/Fantastic_dream/thumb/16/stone_rokey.net.png\">", "Dirt", 25),
    STONE("#", "Stone", 100),
    WOOD("<img src=\"https://iconizer.net/files/Boolean/orig/Tree.png\">", "Wood", 50),
    GOLD("G", "Gold", 85),
    IRON("0", "Iron", 85),
    BEDROCK("░", "Bedrock", 999999999)
}
