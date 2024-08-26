package com.example.lesson1

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var passengerCount = 0
    private val maxSeats = 49

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonPlus.setOnClickListener {
            updatePassengerCount(1)
        }

        binding.buttonMinus.setOnClickListener {
            updatePassengerCount(-1)
        }

        binding.buttonReset.setOnClickListener {
            resetPassengerCount()
        }

        updateUI()
    }

    private fun updatePassengerCount(change: Int) {
        passengerCount = (passengerCount + change).coerceAtLeast(0)
        updateUI()
    }

    private fun resetPassengerCount() {
        passengerCount = 0
        updateUI()
    }

    private fun updateUI() {

        binding.passengerCount.text = passengerCount.toString()


        val seatsLeft = maxSeats - passengerCount
        binding.seatsLeftMessage.text = "Мест осталось: $seatsLeft"


        when {
            passengerCount == 0 -> {
                val green = Color.GREEN
                binding.passengerCount.setTextColor(green)
                binding.seatsLeftMessage.setTextColor(green)
                binding.buttonMinus.isEnabled = false
                binding.buttonReset.visibility = android.view.View.GONE
            }
            passengerCount in 1..49 -> {
                val blue = Color.BLUE
                binding.passengerCount.setTextColor(blue)
                binding.seatsLeftMessage.setTextColor(blue)
                binding.buttonMinus.isEnabled = true
                binding.buttonReset.visibility = android.view.View.GONE
            }
            passengerCount >= 50 -> {
                val red = Color.RED
                binding.seatsLeftMessage.text = "Пассажиров слишком много"
                binding.passengerCount.setTextColor(red)
                binding.seatsLeftMessage.setTextColor(red)
                binding.buttonMinus.isEnabled = true
                binding.buttonReset.visibility = android.view.View.VISIBLE
            }
        }
    }
}



