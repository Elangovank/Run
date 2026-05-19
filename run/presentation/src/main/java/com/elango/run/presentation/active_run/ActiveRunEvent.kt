package com.elango.run.presentation.active_run

sealed interface ActiveRunEvent {
    data class Error(val msg: String) : ActiveRunEvent
    data object RunSuccess : ActiveRunEvent
}