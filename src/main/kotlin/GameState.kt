import game.orders.Order
import game.orders.OrderType

class GameState {
    var gameRunning = true
    var currentMode = OrderType.NONE

    val orders = mutableListOf<Order>()

    fun getOrderForBlock(x: Int, y: Int, z: Int): OrderType? {
        return orders.find { order -> order.x == x && order.y == y && order.z == z  }?.orderType
    }
}

