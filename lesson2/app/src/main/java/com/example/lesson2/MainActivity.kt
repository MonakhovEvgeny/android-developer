package com.example.lesson2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.customPostView.setTopText("Верхняя строчка, настроенная из кода")
        binding.customPostView.setBottomText("Нижняя строчка, настроенная из кода")
    }
}