package com.zybooks.pizzaparty

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

const val SLICES_PER_PIZZA = 8


class MainActivity : AppCompatActivity() {

    private lateinit var numAttendEditText: EditText
    private lateinit var numPizzasTextView: TextView
    private lateinit var howHungryRadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)
    }

    /**
     * TODO: Calculate Total Number of Pizza's needed
     *
     * @param view
     */
    fun calculateClick(view: View) {

        /**
         * Get text for number of people from EditText
         */

        val numAttendStr = numAttendEditText.text.toString()

        /**
         *  Covert number of people from String to Int
         */

        val numAttend = numAttendStr.toInt()

        /**
         *  Decide slice numbers for Light, Medium, and Ravenous
         */

        val slicesPerPerson = when (howHungryRadioGroup.checkedRadioButtonId) {
            R.id.light_radio_button ->2
            R.id.medium_radio_button -> 3
            else -> 4
        }

        /**
         * Calculate total number of pizzas needed and show it in TextView
         */

        val totalPizzas = ceil(numAttend * slicesPerPerson / SLICES_PER_PIZZA.toDouble()).toInt()
        numPizzasTextView.text = "Total pizzas: $totalPizzas"
    }
}