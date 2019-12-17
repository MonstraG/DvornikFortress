package game

import game.objects.BlockType
import game.orders.OrderType
import gameState

private val baseHelp = arrayOf(
    "\tesq: exit game",
    "\tq: rise up",
    "\tz: go down",
    "\tarrows: move around",
    "",
    "\td: dig mode",
    "\tb: build mode",
    "\tc: leave current mode",
    "\tenter: apply order",
    "\tspace: pause/continue"
)

private val buildHelp = arrayOf(
    "",
    "\tNum8: selection up",
    "\tNum2: selection down"
)

//todo make help draw its contents based on gamestate
fun getHelpLines(): Array<String> {
    if (gameState.currentMode == OrderType.BUILD) {
        return baseHelp + buildHelp
    }
    return baseHelp
}

fun displayInventory(): Array<String> {
    return (mutableListOf("============") + gameState.inventory.getDisplay()).toTypedArray()
}