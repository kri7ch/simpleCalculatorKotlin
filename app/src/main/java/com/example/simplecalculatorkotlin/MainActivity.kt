package com.example.simplecalculatorkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var display_input: EditText? = null
    private var firstValue: TextView? = null
    private var isEql = false
    private var operand1 = 0.0
    private var operand2 = 0.0
    private var operator = 0.toChar()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        display_input = findViewById(R.id.display_input)
        firstValue = findViewById(R.id.firstValue)
    }

    fun click_Num(view: View) {
        isEql = false
        if (display_input!!.text.toString() == "0") {
            display_input!!.setText("")
        }
        display_input!!.append((view as Button).text)
    }

    @SuppressLint("SetTextI18n")
    fun clickOperation(view: View) {
        if (display_input!!.text.toString().isEmpty()) {
            return
        }
        if (!java.lang.Double.isNaN(operand1)) {
            click_result(view)
        }
        operand1 = display_input!!.text.toString().toDouble()
        operator = (view as Button).text[0]
        firstValue!!.text = formatNumber(operand1) + " " + operator
        display_input!!.setText("")
    }

    @SuppressLint("SetTextI18n")
    fun clickDot(view: View?) {
        if (display_input!!.text.toString().contains(".")) {
            return
        }
        if (display_input!!.text.toString().isEmpty()) {
            display_input!!.setText("0")
        }
        display_input!!.setText(display_input!!.text.toString() + ".")
    }

    fun click_C(view: View?) {
        display_input!!.setText("0")
        operand1 = Double.NaN
        firstValue!!.text = ""
        operand2 = 0.0
        operator = ' '
    }

    fun click_Ce(view: View?) {
        val currentText = display_input!!.text.toString()
        if (!currentText.isEmpty()) {
            display_input!!.setText(currentText.substring(0, currentText.length - 1))
        }
        if (display_input!!.text.toString().isEmpty()) {
            display_input!!.setText("0")
        }
    }


    private fun formatNumber(number: Double): String {
        if (number == number.toInt().toDouble()) {
            return number.toInt().toString()
        }
        return number.toString()
    }

    @SuppressLint("SetTextI18n")
    fun click_result(view: View?) {
        if (java.lang.Double.isNaN(operand1) || display_input!!.text.toString().isEmpty()) {
            return
        }

        if (display_input!!.text.toString().lowercase(Locale.getDefault()).contains("e")) {
            display_input!!.setText("0")
            click_C(null)
            return
        }

        if (!isEql) {
            operand2 = display_input!!.text.toString().toDouble()
        }
        isEql = true

        when (operator) {
            '+' -> display_input!!.setText(formatNumber(operand1 + operand2))
            '-' -> display_input!!.setText(formatNumber(operand1 - operand2))
            '*' -> display_input!!.setText(formatNumber(operand1 * operand2))
            '/' -> if (operand2 == 0.0) {
                display_input!!.setText("0")
            } else {
                display_input!!.setText(formatNumber(operand1 / operand2))
            }
        }
        firstValue!!.text = formatNumber(operand1) + " " + operator + " " + formatNumber(operand2)
        operand1 = display_input!!.text.toString().toDouble()
    }
}