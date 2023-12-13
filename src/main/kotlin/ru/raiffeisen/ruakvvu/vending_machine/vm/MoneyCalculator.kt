package ru.raiffeisen.ruakvvu.vending_machine.vm

interface MoneyCalculator<M> {
    fun zero(): M
    fun add(s: M, s1: M): M
    fun compare(price: M, sumValue: M): Boolean
}