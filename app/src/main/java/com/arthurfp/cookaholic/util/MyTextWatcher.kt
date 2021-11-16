package com.arthurfp.cookaholic.util

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout


class MyTextListeners(val textInputLayout: TextInputLayout, val errorMessage: String) {
    inner class MyTextWatcher : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            validateEmptyText(s, textInputLayout, errorMessage)
        }

    }

    inner class MyOnFocusChangeListener : View.OnFocusChangeListener {
        override fun onFocusChange(v: View, hasFocus: Boolean) {
            if (!hasFocus) {
                validateEmptyText((v as EditText).text, textInputLayout, errorMessage)
            }
        }

    }

    private fun validateEmptyText(
        s: Editable,
        textInputLayout: TextInputLayout,
        errorMessage: String
    ) {
        if (!s.isNullOrBlank()) {
            textInputLayout.error = null
        } else {
            textInputLayout.error = errorMessage
        }
    }
}