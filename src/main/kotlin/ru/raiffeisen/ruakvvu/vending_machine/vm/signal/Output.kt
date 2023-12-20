package ru.raiffeisen.ruakvvu.vending_machine.vm.signal

import ru.raiffeisen.ruakvvu.vending_machine.vm.`object`.Coin
import ru.raiffeisen.ruakvvu.vending_machine.vm.`object`.Product

sealed interface Output<M, P: Product<M>, C: Coin<M>> {
    class EjectProduct<M, P: Product<M>, C: Coin<M>>(val product: P) : Output<M, P, C>
    class EjectCoin <M, P: Product<M>, C: Coin<M>> (val value: C) :  Output<M, P, C>
    class DisplayProductNotSelected<M, P: Product<M>, C: Coin<M>> : Output<M, P, C>
    class DisplayProductIsOut <M, P: Product<M>, C: Coin<M>> (val product: P) :  Output<M, P, C>
}