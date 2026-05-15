package com.elango.auth.domain

interface PatternValidator {

    fun matches(text: String): Boolean
}