package input

import game.actors.Dwarf
import game.map.Map
import game.objects.BlockType
import gameState
import game.orders.OrderType


import javafx.scene.effect.SepiaTone

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

    fun w() {
        if (gameState.currentMode == OrderType.BUILD) {
            addBuildOrder(game.objects.BlockType.WOOD)
        }
    }



//    fun s() {
//        if (gameState.currentMode == OrderType.BUILD) {
//            gameState.currentBuildBlock = SepiaTone
//        } else {
//
//        }
//    }
}