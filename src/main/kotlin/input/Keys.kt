package input

import gameState
import map.OrderType

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
        cancelMode()
    }

    fun enter() {
        if (gameState.currentMode == OrderType.DIG) {
            addDigOrder()
        }
    }
}