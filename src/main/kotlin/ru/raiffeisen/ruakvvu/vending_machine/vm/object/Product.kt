package ru.raiffeisen.ruakvvu.vending_machine.vm.`object`

interface Product<M> {
    var availableCount: Int
    val price: M
}