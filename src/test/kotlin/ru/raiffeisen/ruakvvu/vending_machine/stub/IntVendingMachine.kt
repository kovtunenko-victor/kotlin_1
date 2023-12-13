package ru.raiffeisen.ruakvvu.vending_machine.stub

import ru.raiffeisen.ruakvvu.vending_machine.vm.MoneyCalculator
import ru.raiffeisen.ruakvvu.vending_machine.vm.VendingMachine

class IntVendingMachine(calc: MoneyCalculator<Int>) : VendingMachine<Int>(calc)