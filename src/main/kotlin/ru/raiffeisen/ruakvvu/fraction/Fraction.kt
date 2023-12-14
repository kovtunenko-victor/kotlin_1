package ru.raiffeisen.ruakvvu.fraction

import java.util.*
import kotlin.math.absoluteValue

class Fraction(n: Int, d: Int) {
    val numerator: Int = prepareNumber(n)
    val denominator: Int = prepareNumber(d, true)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (javaClass != other?.javaClass) {
            return false
        }
        other as Fraction
        if (numerator != other.numerator || denominator != other.denominator) {
            return false
        }
        return true
    }

    override fun hashCode(): Int {
        return Objects.hash(numerator, denominator)
    }

    override fun toString(): String {
        return String.format("%s/%s", numerator, denominator)
    }

    operator fun div(fr: Fraction): Fraction =
        createGcdFraction(
            this.numerator * fr.denominator,
            this.denominator * fr.numerator
        )

    operator fun times(fr: Fraction): Fraction =
        createGcdFraction(
            this.numerator * fr.numerator,
            this.denominator * fr.denominator
        )

    operator fun plus(fr: Fraction): Fraction =
        createGcdFraction(
            this.numerator * fr.denominator + fr.numerator * this.denominator,
            this.denominator * fr.denominator
        )
    operator fun minus(fr: Fraction): Fraction =
        createGcdFraction(
            this.numerator * fr.denominator - fr.numerator * this.denominator,
            this.denominator * fr.denominator
        )

    operator fun compareTo(fr: Fraction): Int =
        this.toDouble().compareTo(fr.toDouble())

    fun toDouble(): Double =
        numerator.toDouble().div(denominator.toDouble())

    private fun createGcdFraction(num: Int, den: Int): Fraction =
        Fraction(num / gcd(num, den), den / gcd(num, den))

    private fun gcd (a: Int, b: Int): Int =
        if(b == 0) a else gcd(b, a % b)

    private fun prepareNumber(value: Int, checkZero: Boolean = false): Int {
        if (value == 0 && checkZero) {
            throw IllegalArgumentException("Denominator eq Zero")
        }
        return value.absoluteValue
    }
}