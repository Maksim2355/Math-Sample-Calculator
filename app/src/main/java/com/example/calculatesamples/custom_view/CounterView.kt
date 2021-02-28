package com.example.calculatesamples.custom_view

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.example.calculatesamples.R

class CounterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private lateinit var titleTv: TextView
    private lateinit var counterEt: EditText
    private lateinit var increaseIbtn: ImageButton
    private lateinit var decreaseIbtn: ImageButton

    var counterNumb: Int = 0
        private set

    var onIncrease: (() -> Unit)? = null
    var onDecrease: (() -> Unit)? = null
    var onChangeNumber: (() -> String)? = null

    var minCounterValue: Int? = null
        set(value) {
            value?.let {
                if (counterNumb < value) {
                    counterNumb = value
                }
            }
            field = value
            updateCounter()
        }

    var maxCounterValue: Int? = null
        set(value) {
            value?.let {
                if (counterNumb > value) {
                    counterNumb = value
                }
            }
            field = value
            updateCounter()
        }

    init {
        inflate(context, R.layout.view_counter, this)
        var title: String? = null
        attrs.let {
            val a = context.theme.obtainStyledAttributes(
                attrs, R.styleable.CounterView, 0, 0
            )
            title = a.getString(R.styleable.CounterView_title)
            a.recycle()
        }

        titleTv = findViewById(R.id.title_tv)
        counterEt = findViewById(R.id.counter_et)
        increaseIbtn = findViewById(R.id.increase_Ibtn)
        decreaseIbtn = findViewById(R.id.decrease_Ibtn)

        titleTv.text = title ?: ""
        updateCounter()
        increaseIbtn.setOnClickListener {
            val maxCounter = maxCounterValue
            if (maxCounter == null){
                counterNumb++
            }else{
                if (counterNumb < maxCounter){
                    counterNumb++
                }
            }
            counterEt.setText(counterNumb.toString())
            onIncrease?.invoke()
        }
        decreaseIbtn.setOnClickListener {
            val minCounter = minCounterValue
            if (minCounter == null){
                counterNumb--
            }else{
                if (counterNumb > minCounter){
                    counterNumb--
                }
            }
            counterEt.setText(counterNumb.toString())
            onDecrease?.invoke()
        }
        counterEt.addTextChangedListener {
            counterNumb = counterEt.text.toString().toIntOrNull() ?: counterNumb
            onChangeNumber?.invoke()
        }
    }

    private fun updateCounter() {
        counterEt.setText(counterNumb.toString())
    }

}