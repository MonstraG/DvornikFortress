package game

import bound
import game.objects.BlockType
import game.orders.OrderType
import gameState

class Inventory {
    class InventoryObj(val block: BlockType, var count: Int) {
        fun plus(count: Int): InventoryObj {
            this.count += count
            return this
        }
    }

    private val inv = mutableListOf<InventoryObj>()
    private var selectedIdx: Int = -1



    private fun add(block: BlockType, count: Int) {
        for (invObj in inv) {
            if (invObj.block == block) {
                invObj.count += count
                if (invObj.count == 0) {
                    inv.remove(invObj)
                    updateSelectedIndex()
                }
                return
            }
        }
        inv.add(InventoryObj(block, 1))
    }

    private fun updateSelectedIndex() {
        selectedIdx = if (inv.size == 0) {
            -1
        } else {
            selectedIdx.bound(0, inv.size)
        }
    }

    fun add(block: BlockType) {
        add(block, 1)
    }

    fun remove(block: BlockType) {
        add(block, -1)
    }

    fun get(block: BlockType): InventoryObj? {
        return inv.find { it.block == block }
    }

    fun has(block: BlockType): Boolean {
        return get(block) != null
    }

    fun get(idx: Int): InventoryObj {
        return inv[idx]
    }

    fun getSelected(): InventoryObj? {
        return inv[selectedIdx]
    }

    fun moveSelectedUp() {
        selectedIdx = (selectedIdx - 1).bound(0, inv.size)
    }

    fun moveSelectedDown() {
        selectedIdx = (selectedIdx + 1).bound(0, inv.size)
    }

    private fun autoAssignSelected() {
        if (selectedIdx == -1 && inv.size > 0) {
            selectedIdx = 0
        }
    }

    fun getDisplay(): List<String> {
        autoAssignSelected()
        return inv.map { getLine(it) }
    }

    private fun getLine(inventoryObj: InventoryObj): String {
        return if (selectedIdx != -1) {
            val selectedPrefix: String = if (getSelected()!!.block == inventoryObj.block && gameState.currentMode == OrderType.BUILD) ">" else ""
            "$selectedPrefix ${inventoryObj.block.locale}: ${inventoryObj.count}"
        } else {
            "${inventoryObj.block.locale}: ${inventoryObj.count}"
        }
    }
}