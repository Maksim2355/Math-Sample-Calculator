package com.example.calculatesamples.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.setOnSampleInputFilter() {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (text.length > 2 && text[text.length - 1] == ' ' && text[text.length - 2] == ' ') {
                setText(text.dropLast(1))
                setSelection(text.length)
            }
        }

        override fun afterTextChanged(s: Editable?) {
        }

    })
}
