package ru.raiffeisen.ruakvvu.vending_machine.vm.state

import ru.raiffeisen.ruakvvu.vending_machine.vm.`object`.Product

sealed interface State<M, P: Product<M>> {
    class Idle<M, P: Product<M>>: State<M, P>
    class Paying<M, P: Product<M>>(val product: P, val sumCoinValue: M) : State<M, P>
}