package ru.raiffeisen.ruakvvu.fraction

class FractionBuilder(val num: Int) {
    operator fun div(num: Int): Fraction = Fraction(this.num, num)
    operator fun div(frb: FractionBuilder): Fraction = Fraction(this.num, frb.num)
}