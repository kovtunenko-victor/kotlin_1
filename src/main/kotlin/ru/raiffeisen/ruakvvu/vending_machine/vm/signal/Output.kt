package ru.raiffeisen.ruakvvu.vending_machine.vm.signal

import ru.raiffeisen.ruakvvu.vending_machine.vm.`object`.Coin
import ru.raiffeisen.ruakvvu.vending_machine.vm.`object`.Product

sealed interface Output<O> {
    data class EjectProduct<M>(val product: Product<M>) : Output<M>
    data class EjectCoin <M> (val value: Coin<M>) :  Output<M>
    class DisplayProductNotSelected<M> : Output<M>
    class Empty<M> : Output<M>
    data class DisplayProductIsOut <M> (val product: Product<M>) :  Output<M>
}