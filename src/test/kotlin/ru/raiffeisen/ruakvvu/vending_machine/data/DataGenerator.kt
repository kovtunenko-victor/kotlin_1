package ru.raiffeisen.ruakvvu.vending_machine.data

import ru.raiffeisen.ruakvvu.vending_machine.stub.IntCoin
import ru.raiffeisen.ruakvvu.vending_machine.stub.IntMoneyCalculator
import ru.raiffeisen.ruakvvu.vending_machine.stub.IntProduct
import ru.raiffeisen.ruakvvu.vending_machine.stub.IntVendingMachine

class DataGenerator {
    val coin5 = IntCoin(5)
    val coin10 = IntCoin(10)
    val product0 = IntProduct(0, 10)
    val product1 = IntProduct(1, 10)

    val machine = IntVendingMachine(IntMoneyCalculator())
}