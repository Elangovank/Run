package com.elango.auth.presentation.register

import com.elango.core.presentation.ui.UiText

sealed interface RegisterEvent {

    data object RegisterSuccess : RegisterEvent
    data class RegisterFailed(val msg: UiText) : RegisterEvent
}