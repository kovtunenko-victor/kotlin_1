package ru.raiffeisen.ruakvvu.vending_machine.stub

import ru.raiffeisen.ruakvvu.vending_machine.vm.`object`.Coin

data class IntCoin(override val value: Int) : Coin<Int>