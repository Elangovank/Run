package com.elango.run.presentation.active_run

import com.elango.core.domain.location.Location
import com.elango.run.domain.RunData
import kotlin.time.Duration

data class ActiveRunState(
    val elapsedTime: Duration = Duration.ZERO,
    val shouldTrack: Boolean = false,
    val hasStartedLocation: Boolean = false,
    val isRunningFinished: Boolean = false,
    val isSavingRun: Boolean = false,
    val currentLocation: Location? = null,
    val runData: RunData = RunData()
)
