package com.elango.auth.data

import android.util.Patterns
import com.elango.auth.domain.PatternValidator

object EmailPatternValidator : PatternValidator {
    override fun matches(text: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(text).matches()
    }
}