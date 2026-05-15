package com.elango.auth.presentation.intro

sealed interface IntroAction {
    data object onSignUpAction : IntroAction
    data object onSignInAction : IntroAction
}