package ru.raiffeisen.ruakvvu.vending_machine.vm

import ru.raiffeisen.ruakvvu.vending_machine.vm.`object`.Product

sealed interface State<out M> {
    object Idle : State<Nothing>
    data class Paying<M>(val product: Product<M>, val sumCoinValue: M) : State<M>
}