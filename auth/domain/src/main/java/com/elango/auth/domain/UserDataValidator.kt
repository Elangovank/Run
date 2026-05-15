package com.elango.auth.domain

class UserDataValidator(
    val patternValidator: PatternValidator
) {

    fun isValidEmail(email: String): Boolean =
        patternValidator.matches(email.trim())

    fun validatePassword(password: String): PasswordValidationState {
        val hasDigit = password.any { it.isDigit() }
        val hasLength = password.length >= MIN_PASSWORD_LENGTH
        val hasUppercase = password.any { it.isUpperCase() }
        val hasLowercase = password.any { it.isLowerCase() }
        return PasswordValidationState(
            hasNumber = hasDigit,
            hasUpperCaseCharacter = hasUppercase,
            hasLowerCaseCharacter = hasLowercase,
            hasMinimumLength = hasLength
        )
    }

    companion object {
        val MIN_PASSWORD_LENGTH = 9
    }
}