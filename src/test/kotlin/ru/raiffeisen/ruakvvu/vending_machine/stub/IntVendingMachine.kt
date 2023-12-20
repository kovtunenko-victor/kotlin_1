package ru.raiffeisen.ruakvvu.vending_machine.stub

import ru.raiffeisen.ruakvvu.vending_machine.vm.MoneyCalculator
import ru.raiffeisen.ruakvvu.vending_machine.vm.VendingMachine
import ru.raiffeisen.ruakvvu.vending_machine.vm.`object`.Coin
import ru.raiffeisen.ruakvvu.vending_machine.vm.`object`.Product

class IntVendingMachine(calc: MoneyCalculator<Int>) : VendingMachine<Int, Product<Int>, Coin<Int>>(calc)