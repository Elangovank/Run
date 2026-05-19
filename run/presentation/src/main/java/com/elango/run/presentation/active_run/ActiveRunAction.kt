package com.elango.run.presentation.active_run

sealed interface ActiveRunAction {
    data object onToggleRunClick : ActiveRunAction
    data object onResumeRunClick : ActiveRunAction
    data object onFinishRunClick : ActiveRunAction
    data object onBackClick : ActiveRunAction
}