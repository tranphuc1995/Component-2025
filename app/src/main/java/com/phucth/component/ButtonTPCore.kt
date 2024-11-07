package com.phucth.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.phucth.component.databinding.ButtonTpCoreBinding

open class ButtonTPCore @JvmOverloads constructor(
    private val context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    protected val binding by lazy {
        ButtonTpCoreBinding.inflate(LayoutInflater.from(context), this, true)
    }

    private var colorButton = 0
    private var colorTextButton = 0

    init {
        initView(attrs)
    }

    private fun initView(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.ButtonTPCore)
        colorButton = ta.getColor(
            R.styleable.ButtonTPCore_buttonColor,
            ContextCompat.getColor(context, R.color.black)
        )
        colorTextButton = ta.getColor(
            R.styleable.ButtonTPCore_buttonTextColor,
            ContextCompat.getColor(context, R.color.white)
        )
        binding.tvText.text = ta.getString(R.styleable.ButtonTPCore_buttonText) ?: ""
        binding.tvText.setTextColor(colorTextButton)
        binding.btn.setCardBackgroundColor(colorButton)
        setStatusButton(ta.getInteger(R.styleable.ButtonTPCore_buttonStatus, 1))
        ta.recycle()
    }

    private fun setStatusButton(buttonStatus: Int) {
        when (buttonStatus) {
            ButtonStatus.LOADING.status -> {
                binding.pbLoading.visibility = View.VISIBLE
                binding.tvText.visibility = View.GONE
            }

            ButtonStatus.ACTIVE.status -> {
                binding.pbLoading.visibility = View.GONE
                binding.tvText.visibility = View.VISIBLE
                binding.btn.setCardBackgroundColor(colorButton)
                binding.tvText.setTextColor(colorTextButton)
            }

            ButtonStatus.DISABLE.status -> {
                binding.pbLoading.visibility = View.GONE
                binding.tvText.visibility = View.VISIBLE
                binding.btn.setCardBackgroundColor(
                    ContextCompat.getColor(context, R.color.disable)
                )
                binding.tvText.setTextColor(ContextCompat.getColor(context, R.color.white))
            }
        }
    }

    fun onButtonClick(onClick: (() -> Unit)) {
        binding.root.setOnClickListener {
            onClick.invoke()
        }
    }

    enum class ButtonStatus(val status: Int) {
        LOADING(0),
        ACTIVE(1),
        DISABLE(2)
    }
}