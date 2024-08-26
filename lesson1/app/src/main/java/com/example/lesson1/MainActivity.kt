package com.example.lesson1


import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var passengerCount = 0

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

        when {
            passengerCount == 0 -> {
                binding.passengerCount.setTextColor(Color.GREEN)
                binding.buttonMinus.isEnabled = false
                binding.buttonReset.visibility = android.view.View.GONE
            }
            passengerCount in 1..49 -> {
                binding.passengerCount.setTextColor(Color.BLUE)
                binding.buttonMinus.isEnabled = true
                binding.buttonReset.visibility = android.view.View.GONE
            }
            passengerCount >= 50 -> {
                binding.passengerCount.setTextColor(Color.RED)
                binding.buttonMinus.isEnabled = true
                binding.buttonReset.visibility = android.view.View.VISIBLE
            }
        }
    }
}

