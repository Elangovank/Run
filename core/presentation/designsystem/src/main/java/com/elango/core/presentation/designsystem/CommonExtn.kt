package com.elango.core.presentation.designsystem

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.distinctUntilChanged


fun TextFieldState.textAsFlow() = snapshotFlow { text.toString() }.distinctUntilChanged()