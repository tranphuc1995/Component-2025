package com.phucth.component

import android.content.Context
import android.util.AttributeSet

class MyCustomButton @JvmOverloads constructor(
    private val context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = 0
) : ButtonTPCore(context, attrs, defStyleAttr) {
    init {
        binding.tvText.text ="asdadasd"
    }
}