package ru.raiffeisen.ruakvvu.fraction

import ru.raiffeisen.ruakvvu.fraction.Fraction
import ru.raiffeisen.ruakvvu.fraction.Fraction as fr

fun main(args: Array<String>) {
    val x = Fraction(1, 10)
    val y = 1.fr / 3
    val z = x / y

    println("$x / $y = $z")
    println("y is around ${y.toDouble()}")
    println("x == 1/5 = ${x == 1.fr / 5}")
    println("x < y = ${x < y}")
    println("-2/3 + 1/3 = ${fr(-2, 3) + fr(1, 3)}")
}
