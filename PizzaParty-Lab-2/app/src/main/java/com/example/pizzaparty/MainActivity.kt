package com.example.pizzaparty

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

const val TAG = "MainActivity"
private const val KEY_TOTAL_PIZZAS = "totalPizzas"

/**
 * MainActivity takes user input and displays output on the screen.
 * It acts as a controller
 */
class MainActivity : AppCompatActivity() {

    private lateinit var numAttendEditText: EditText
    private lateinit var numPizzasTextView: TextView
    private lateinit var howHungryRadioGroup: RadioGroup
    private var totalPizzas = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate was called")

        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)
        val totalText = getString(R.string.total_pizzas, totalPizzas)
        numPizzasTextView.text = totalText
        // Restore state
        if (savedInstanceState != null) {
            totalPizzas = savedInstanceState.getInt(KEY_TOTAL_PIZZAS)
            displayTotal()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_TOTAL_PIZZAS, totalPizzas)
    }
    /**
     * The actual business logic is in the file CalculatePizza.kt
     * Total number of pizzas can be calculated by creating the object of CalculatePizza
     */
    fun calculateClick(view: View) {

        // Get the text that was typed into the EditText
        val numAttendStr = numAttendEditText.text.toString()

        // Convert the text into an integer
        val numAttend = numAttendStr.toIntOrNull() ?: 0

        // Get hunger level selection
        val hungerLevel = when (howHungryRadioGroup.getCheckedRadioButtonId()) {
            R.id.light_radio_button -> CalculatePizza.HungerLevel.LIGHT
            R.id.medium_radio_button -> CalculatePizza.HungerLevel.MEDIUM
            else -> CalculatePizza.HungerLevel.RAVENOUS
        }

        // Get the number of pizzas needed
        val calc = CalculatePizza(numAttend, hungerLevel)
        totalPizzas = calc.totalPizzas

        displayTotal()
    }

    private fun displayTotal() {
        val totalText = getString(R.string.total_pizzas, totalPizzas)
        numPizzasTextView.text = totalText
    }
}