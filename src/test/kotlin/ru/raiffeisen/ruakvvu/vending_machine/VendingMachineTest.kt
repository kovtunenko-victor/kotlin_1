package ru.raiffeisen.ruakvvu.vending_machine

import org.junit.jupiter.api.Test
import ru.raiffeisen.ruakvvu.vending_machine.data.DataGenerator
import ru.raiffeisen.ruakvvu.vending_machine.vm.`object`.Coin
import ru.raiffeisen.ruakvvu.vending_machine.vm.`object`.Product
import ru.raiffeisen.ruakvvu.vending_machine.vm.signal.Input
import kotlin.test.assertEquals

class VendingMachineTest {
    private val dataGenerator: DataGenerator = DataGenerator()

    @Test
    fun vendingMachine_shouldEjectSelectedProductAfterInsertCoin() {
        val inputCoin = Input.InsertCoin<Int, Product<Int>, Coin<Int>>(dataGenerator.coin5)
        val inputProduct = Input.SelectProduct<Int, Product<Int>, Coin<Int>>(dataGenerator.product1)

        var actual = dataGenerator.machine.action(inputProduct).map { t -> t.javaClass.simpleName }
        assertEquals(actual.size, 0)

        actual = dataGenerator.machine.action(inputCoin).map { t -> t.javaClass.simpleName }
        assertEquals(actual.size, 0)

        actual = dataGenerator.machine.action(inputCoin).map { t -> t.javaClass.simpleName }
        assertEquals(actual, listOf("EjectProduct"))
    }

    @Test
    fun vendingMachine_shouldEjectFirstSelectedProductAfterTwoProductSelectionsAndInsertCoin() {
        val inputCoin = Input.InsertCoin<Int, Product<Int>, Coin<Int>>(dataGenerator.coin10)
        val inputProduct1 = Input.SelectProduct<Int, Product<Int>, Coin<Int>>(dataGenerator.product1)
        val inputProduct0 = Input.SelectProduct<Int, Product<Int>, Coin<Int>>(dataGenerator.product0)

        var actual = dataGenerator.machine.action(inputProduct1).map { t -> t.javaClass.simpleName }
        assertEquals(actual.size, 0)

        actual = dataGenerator.machine.action(inputProduct0).map { t -> t.javaClass.simpleName }
        assertEquals(actual.size, 0)

        actual = dataGenerator.machine.action(inputCoin).map { t -> t.javaClass.simpleName }
        assertEquals(actual, listOf("EjectProduct"))
    }

    @Test
    fun vendingMachine_shouldDisplayProductIsOut_afterSelectOutProduct() {
        val inputProduct0 = Input.SelectProduct<Int, Product<Int>, Coin<Int>>(dataGenerator.product0)

        val actual = dataGenerator.machine.action(inputProduct0).map { t -> t.javaClass.simpleName }
        assertEquals(actual, listOf("DisplayProductIsOut"))
    }

    @Test
    fun vendingMachine_shouldDisplayProductNotSelectedAndEjectCoin_afterInsertCoinBeforeSelectProduct() {
        val inputCoin = Input.InsertCoin<Int, Product<Int>, Coin<Int>>(dataGenerator.coin10)

        val actual = dataGenerator.machine.action(inputCoin).map { t -> t.javaClass.simpleName }
        assertEquals(actual, listOf("EjectCoin", "DisplayProductNotSelected"))
    }
}