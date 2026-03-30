package com.example.calculadora.Model
class CalculatorModel {
    data class HistoryItem(
        val expression: String,
        val result: String
    )
    fun add(num1: Double?, num2: Double?): Double {
        return (num1 ?: 0.0) + (num2 ?: 0.0)
    }

    fun subtract(num1: Double?, num2: Double?): Double {
        return (num1 ?: 0.0) - (num2 ?: 0.0)
    }

    fun multiply(num1: Double?, num2: Double?): Double {
        return (num1 ?: 0.0) * (num2 ?: 0.0)
    }

    fun divide(num1: Double?, num2: Double?): Double {
        if (num2 == null || num2 == 0.0) {
            throw IllegalArgumentException("No dividir por 0")
        }
        return (num1 ?: 0.0) / num2
    }
}