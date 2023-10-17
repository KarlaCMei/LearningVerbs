package com.karla.learningverbs.utils

import android.util.Patterns

object StringUtils {
    fun validateEmail(text: CharSequence?): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return text != null && text.toString() != "" && pattern.matcher(
            text.toString().trim { it <= ' ' }).matches()
    }

    fun validatePassword(text: CharSequence?): Boolean {
        return text != null && text.toString() != "" && text.toString().length > 6
    }

    fun validateSamePassword(text: CharSequence?, text2: CharSequence): Boolean {
        return text != null && text.toString() != "" && text != text2
    }
}