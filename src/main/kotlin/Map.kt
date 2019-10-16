import kotlin.random.Random
import kotlin.reflect.typeOf

var Help = arrayOf(
    "\tСправка:",
    "\t",
    "\tq: выход из игры",
    "\tu: подняться на один уровень",
    "\td: опуститься на один уровень",
    "\tУправление игроком осуществляется",
    "\tчерез стрелки"
)

enum class Material {
    NONE,
    DIRT,
    STONE,
    GOLD,
    IRON
}

//map to char instead of string to ensure that every material has 1-char representation.
val MaterialToCharMap = mapOf(
    Material.NONE to ' ',
    Material.DIRT to '\'',
    Material.STONE to '#',
    Material.GOLD to '$',
    Material.IRON to 'o'
)

class Block(val material: Material = Material.NONE) {
    
    override fun toString(): String {
        return MaterialToCharMap[this.material].toString()
    }
}

class Map(val height: Int = 256, val width: Int = 256, val depth: Int = 64) {
    val cursor = Cursor()
    var map = Array(depth){z->Array(height) {Array(width) { Block(gen(z)) }}}

    fun gen(z:Int): Material {
        var material: Material //если z > 17, то все воздух (None) 17 - уровень земли. все, что ниже 17 - это камень/золото/железо
        if (z > 17){
            material = Material.NONE
        }
        else{
        if (z == 17)
            material = Material.DIRT
        else{ //ВОТ ТУТА НАЧИНАЕТСЯ ВЕРОЯТНОСТНОЕ РАСПРЕДЕЛЕНИЕ
            val random = Random
            val percent = random.nextFloat()
            if (percent < 0.1)
                material = Material.GOLD
            else{
                if (percent < 0.2)
                    material = Material.IRON
                else
                    material = Material.STONE
            }
        }
        }
        return material
    }

    fun getVisibleWidthBounds(): Pair<Int, Int> {
        return Pair(
            cursor.posX - cursor.width,
            cursor.posX + cursor.width
        )
    }

    fun getVisibleHeightBounds(): Pair<Int, Int> {
        return Pair(
            cursor.posY - cursor.height,
            cursor.posY + cursor.height
        )
    }
}

class Cursor {
    //current position of the screen center
    var posX = 0
    var posY = 0
    var posZ = 17; //will be added later

    //we draw %width blocks to the left of the cursor, and same amount to the right
    var width = 30
    var height = 12
}
