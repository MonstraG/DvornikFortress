import GameState.GameStateConstants.ASSIGNMENT_WAIT_TIME
import GameState.GameStateConstants.DAY_LENGTH
import game.Inventory
import game.actors.Dwarf
import game.orders.Order
import game.orders.OrderType


class GameState {
    object GameStateConstants {
        const val DAY_LENGTH = 24000
        const val ASSIGNMENT_WAIT_TIME = 15
    }

    var gameRunning = true
    var currentMode = OrderType.NONE

    val orders = mutableListOf<Order>()
    val dwarfs = mutableListOf<Dwarf>()

    var tick = 0
    private var paused = false

    var imageMode = false

    fun completeOrder(order: Order) {
        orders.remove(order)
        order.dispose()
    }

    var inventory = Inventory()

    fun tick() {
        if (!paused) {
            addTick()
            assignOrders()
            moveDwarfs()
        }
    }

    private fun addTick() {
        tick += 1
        if (tick > DAY_LENGTH)
            tick = 0
    }

    fun pause() {
        paused = !paused
    }

    private fun assignOrders() {
        if (tick % ASSIGNMENT_WAIT_TIME != 0) {
            return
        }

        dwarfs.filter { it.unassigned() }
            .zip(orders.filter { it.unassigned() })
            .forEach { (dwarf, order) ->
                dwarf.assignment = order
                order.assignee = dwarf
                println("Dwarf ${dwarf.name} assigned to ${order.orderType} order on [${order.x}, ${order.y}, ${order.z}]")
            }
    }

    private fun moveDwarfs() {
        dwarfs.forEach { if (tick % Dwarf.DwarfConstants.DWARF_SPEED == 0) it.act() }
    }

    fun getOrderForBlock(x: Int, y: Int, z: Int): OrderType? {
        return orders.find { order -> order.x == x && order.y == y && order.z == z }?.orderType
    }

    fun getOrderForBlockOrBelow(x: Int, y: Int, z: Int): OrderType? {
        return getOrderForBlock(x, y, z) ?: return getOrderForBlock(x, y, z - 1)
    }
}

