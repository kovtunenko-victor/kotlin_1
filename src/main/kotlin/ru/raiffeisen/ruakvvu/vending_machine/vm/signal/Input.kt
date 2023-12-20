package ru.raiffeisen.ruakvvu.vending_machine.vm.signal

import ru.raiffeisen.ruakvvu.vending_machine.vm.`object`.Coin
import ru.raiffeisen.ruakvvu.vending_machine.vm.`object`.Product

sealed interface Input<M, P: Product<M>, C: Coin<M>> {
    data class InsertCoin<M, P: Product<M>, C: Coin<M>> (val coin: C): Input<M, P, C>
    data class SelectProduct<M, P: Product<M>, C: Coin<M>> (val product: P) : Input<M, P, C>
}