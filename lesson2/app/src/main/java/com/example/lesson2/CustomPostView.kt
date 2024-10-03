package com.example.lesson2

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.lesson2.databinding.CustomPostViewBinding

class CustomPostView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: CustomPostViewBinding

    init {
        val inflater = LayoutInflater.from(context)
        binding = CustomPostViewBinding.inflate(inflater, this, true)
    }

    fun setTopText(text: String) {
        binding.topText.text = text
    }

    fun setBottomText(text: String) {
        binding.bottomText.text = text
    }
}