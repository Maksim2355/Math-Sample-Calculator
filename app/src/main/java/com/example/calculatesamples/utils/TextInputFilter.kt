package com.example.calculatesamples.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.setOnSampleInputFilter() {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            TODO("Not yet implemented")
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val lenText = text.length ?: 0
            if (lenText > 1 && (text[lenText] == ' ' && text[lenText] == ' ')) {
                setText(text.dropLast(1))
            }
        }

        override fun afterTextChanged(s: Editable?) {
            TODO("Not yet implemented")
        }

    })
}
