package map

enum class OrderType(val hoverColor: String,val color: String) {
    NONE("red", "white"),
    DIG("blue", "#01579B")
}

class Order(val orderType: OrderType, val x: Int, val y: Int, val z: Int)

