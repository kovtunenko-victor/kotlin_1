package ru.raiffeisen.ruakvvu.vending_machine.stub

import ru.raiffeisen.ruakvvu.vending_machine.vm.`object`.Product

data class IntProduct(override var availableCount: Int, override val price: Int) : Product<Int>
