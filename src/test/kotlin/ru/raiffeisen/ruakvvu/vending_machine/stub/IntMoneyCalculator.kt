package ru.raiffeisen.ruakvvu.vending_machine.stub

import ru.raiffeisen.ruakvvu.vending_machine.vm.MoneyCalculator


class IntMoneyCalculator : MoneyCalculator<Int> {
    override fun zero(): Int = 0

    override fun add(s: Int, s1: Int): Int = s + s1

    override fun compare(price: Int, sumValue: Int): Boolean = price > sumValue
}