package com.example.calculadora.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.calculadora.Model.CalculatorModel

class CalculatorViewModel: ViewModel() {

    var history = mutableStateOf(listOf<CalculatorModel.HistoryItem>())
    var expression = mutableStateOf("")
    var result = mutableStateOf("0")

    private var num1 = 0.0
    private var op: String? = null

    fun changeSign() {
        val value = result.value.toDouble()
        val res = value * -1
        result.value = formatResult(res)
    }
    fun porcentaje() {
        val value = result.value.toDouble()
        val res = value / 100
        result.value = formatResult(res)
    }
    fun deleteLast() {
        result.value = if (result.value.length > 1) {
            result.value.dropLast(1)
        } else {
            "0"
        }
    }
    fun onNumberClick(number: String) {
        result.value = if (result.value == "0") number else result.value + number
    }

    fun onOperatorClick(operator: String) {
        num1 = result.value.toDouble()
        op = operator


        expression.value = "${result.value} $operator"

        result.value = "0"
    }

    fun onEqualClick() {
        val num2 = result.value.toDouble()

        val res = when (op) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "X" -> num1 * num2
            "÷" -> num1 / num2
            else -> num2
        }

        val finalResult = formatResult(res)

        val fullExpression = "${formatResult(num1)} $op ${formatResult(num2)}"

        history.value = history.value + CalculatorModel.HistoryItem(
            expression = fullExpression,
            result = finalResult
        )

        expression.value = fullExpression
        result.value = finalResult
    }
    fun formatResult(value: Double): String {
        return if (value % 1.0 == 0.0) {
            value.toInt().toString()
        } else {
            "%.6f".format(value).trimEnd('0').trimEnd('.')
        }
    }

    fun clear() {
        result.value = "0"
        expression.value = ""
    }
}