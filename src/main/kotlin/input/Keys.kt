package input

import digMode

object Keys {
    fun esq() {
        exit()
    }

    fun q() {
        if (!digMode) {
            riseUp()
        }
    }

    fun z() {
        if (!digMode) {
            goDown()
        }
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
        if (!digMode) {
            enterDigMode()
        }
    }

    fun enter() {
        if (digMode) {
            finalizeDigOrder()
        }
    }
}