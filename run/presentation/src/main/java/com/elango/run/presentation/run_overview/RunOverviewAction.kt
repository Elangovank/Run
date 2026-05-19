package com.elango.run.presentation.run_overview

sealed interface RunOverviewAction {
    data object onStartRunClick : RunOverviewAction
    data object onLogoutClick : RunOverviewAction
    data object onAnalyticsClick : RunOverviewAction
}