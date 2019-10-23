package map.objects

//todo: create class BlockType, and fill come kind of blockType list with them like so:
// BlockType(Id, map.objects.Material, MapChar, Locale)

//class BlockType(Id: Int, TypeOfMaterial:Material, MapChar: Char, Locale:String){
//
//}
//class B
enum class Material {
    NONE,
    DIRT,
    STONE,
    WOOD,
    GOLD,
    IRON,
    BEDROCK
}

val MaterialLocale = mapOf(
    Material.DIRT to "Dirt",
    Material.IRON to "Iron",
    Material.GOLD to "Gold",
    Material.STONE to "Stone",
    Material.WOOD to "Wood",
    Material.BEDROCK to "Bedrock",
    Material.NONE to "Air")

val MaterialToCharMap = mapOf(
    Material.NONE to "&nbsp;",
    Material.DIRT to '\'',
    Material.STONE to '#',
    Material.WOOD to 'O',
    Material.GOLD to 'G',
    Material.IRON to '0',
    Material.BEDROCK to '░'
)
