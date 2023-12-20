package ru.raiffeisen.ruakvvu.vending_machine.vm

import ru.raiffeisen.ruakvvu.vending_machine.vm.`object`.Coin
import ru.raiffeisen.ruakvvu.vending_machine.vm.`object`.Product
import ru.raiffeisen.ruakvvu.vending_machine.vm.signal.Input
import ru.raiffeisen.ruakvvu.vending_machine.vm.signal.Output
import ru.raiffeisen.ruakvvu.vending_machine.vm.state.State


abstract class VendingMachine<M, P: Product<M>, C: Coin<M>> (
    private val calc: MoneyCalculator<M>
    ) {
    private var state: State<M, P> = State.Idle()

    fun action(input: Input<M, P, C>) : List<Output<M, P, C>>  {
        when(state) {
            is State.Idle -> {
                when(input) {
                    is Input.InsertCoin<M, P, C> -> {
                        return listOf(Output.EjectCoin(input.coin), Output.DisplayProductNotSelected())
                    }
                    is Input.SelectProduct<M, P, C> -> {
                        if(input.product.availableCount == 0) {
                            return listOf(Output.DisplayProductIsOut(input.product))
                        } else {
                            state = State.Paying(input.product, calc.zero())
                        }
                    }
                }
            }
            is State.Paying<M, P> -> {
                val paying = state as State.Paying<M, P>
                when(input) {
                    is Input.InsertCoin<M, P, C> -> {
                        val currentCoinValueSum = calc.add(paying.sumCoinValue, input.coin.value)
                        if(calc.compare(paying.product.price, currentCoinValueSum)) {
                            state = State.Paying(paying.product, currentCoinValueSum)
                            return emptyList()
                        }
                        state = State.Idle()
                        return listOf(Output.EjectProduct(paying.product))
                    }
                    else -> return emptyList()
                }
            }
        }
        return emptyList()
    }
}

