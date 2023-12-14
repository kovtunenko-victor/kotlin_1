package ru.raiffeisen.ruakvvu.fraction

val Int.fr get() = FractionBuilder(this)
operator fun Int.div(frb: FractionBuilder): Fraction = Fraction(this, frb.num)