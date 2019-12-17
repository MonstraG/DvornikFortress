package input

import game.objects.BlockType
import gameState
import game.orders.OrderType

object Keys {
    fun esq() {
        exit()
    }

    fun q() {
        riseUp()
    }

    fun z() {
        goDown()
    }

    fun upArrow() {
        moveUp()
    }

    fun downArrow() {
        moveDown()
    }

    fun leftArrow() {
        moveLeft()
    }

    fun rightArrow() {
        moveRight()
    }

    fun d() {
        if (gameState.currentMode != OrderType.DIG) {
            enterDigMode()
        }
    }

    fun c() {
        leaveAnyMode()
    }

    fun enter() {
        when (gameState.currentMode) {
            OrderType.DIG -> addDigOrder()
            OrderType.BUILD -> {
                val selectedBlock = gameState.inventory.getSelected()
                if (selectedBlock != null) {
                    addBuildOrder(selectedBlock.block)
                }
            }
            else -> return
        }
    }

    fun space() {
        pause()
    }

    fun i() {
        changeGraphicsMode()
    }

    fun b(){
        if (gameState.currentMode != OrderType.BUILD) {
            enterBuildMode()
        }
    }

    fun numpad8(){
        moveUpInInventory()
    }

    fun numpad2(){
        moveDownInInventory()
    }
}