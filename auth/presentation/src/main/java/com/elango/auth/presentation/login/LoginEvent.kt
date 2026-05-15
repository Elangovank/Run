package com.elango.auth.presentation.login

import com.elango.core.presentation.ui.UiText

sealed interface LoginEvent {
    data class onError(val msg: UiText) : LoginEvent
    data object onSuccess : LoginEvent

}