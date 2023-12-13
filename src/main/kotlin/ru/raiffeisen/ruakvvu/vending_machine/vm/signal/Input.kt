package ru.raiffeisen.ruakvvu.vending_machine.vm.signal

import ru.raiffeisen.ruakvvu.vending_machine.vm.`object`.Coin
import ru.raiffeisen.ruakvvu.vending_machine.vm.`object`.Product

sealed interface Input<M> {
    data class InsertCoin<M> (val coin: Coin<M>): Input<M>
    data class SelectProduct<M> (val product: Product<M>) : Input<M>
}