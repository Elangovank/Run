package com.elango.auth.presentation.login

sealed interface LoginAction {
    data object onPasswordToggle : LoginAction
    data object onLoginClick : LoginAction
    data object onSignUpClick : LoginAction
}