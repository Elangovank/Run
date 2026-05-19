package com.elango.run.presentation.active_run;

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

public class ActiveRunViewModel : ViewModel() {


    var state by mutableStateOf(
        ActiveRunState()
    )

    private var eventChannel = Channel<ActiveRunEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: ActiveRunAction) {

    }
}
