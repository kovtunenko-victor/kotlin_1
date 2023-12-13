package ru.raiffeisen.ruakvvu.vending_machine.vm

import ru.raiffeisen.ruakvvu.vending_machine.vm.signal.Input
import ru.raiffeisen.ruakvvu.vending_machine.vm.signal.Output


abstract class VendingMachine<M> (private val calc: MoneyCalculator<M>) {
    private var state: State<M> = State.Idle

    fun action(input: Input<M>) : List<Output<M>>  {
        when(state) {
            is State.Idle -> {
                when(input) {
                    is Input.InsertCoin<M> -> {
                        return listOf(Output.EjectCoin(input.coin), Output.DisplayProductNotSelected())
                    }
                    is Input.SelectProduct<M> -> {
                        if(input.product.availableCount == 0) {
                            return listOf(Output.DisplayProductIsOut(input.product))
                        } else {
                            state = State.Paying(input.product, calc.zero())
                        }
                    }
                }
            }
            is State.Paying<M> -> {
                val paying = state as State.Paying<M>
                when(input) {
                    is Input.InsertCoin<M> -> {
                        val currentCoinValueSum = calc.add(paying.sumCoinValue, input.coin.value)
                        if(calc.compare(paying.product.price, currentCoinValueSum)) {
                            state = State.Paying(paying.product, currentCoinValueSum)
                            return listOf(Output.Empty())
                        }
                        state = State.Idle
                        return listOf(Output.EjectProduct(paying.product))
                    }
                    else -> return listOf(Output.Empty())
                }
            }
        }
        return listOf(Output.Empty())
    }
}

