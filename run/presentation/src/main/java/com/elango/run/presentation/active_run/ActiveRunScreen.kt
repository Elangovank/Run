@file:OptIn(ExperimentalMaterial3Api::class)

package com.elango.run.presentation.active_run

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elango.core.presentation.designsystem.RunIcon
import com.elango.core.presentation.designsystem.RunTheme
import com.elango.core.presentation.designsystem.StartIcon
import com.elango.core.presentation.designsystem.StopIcon
import com.elango.core.presentation.designsystem.components.RunFloatingActionButton
import com.elango.core.presentation.designsystem.components.RunScaffold
import com.elango.core.presentation.designsystem.components.RunToolbar
import com.elango.run.presentation.R
import com.elango.run.presentation.active_run.components.RunDataCard
import org.koin.androidx.compose.koinViewModel


@Composable
fun ActiveRunScreenRoot(
    viewModel: ActiveRunViewModel = koinViewModel()
) {
    ActiveRunScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun ActiveRunScreen(
    state: ActiveRunState,
    onAction: (ActiveRunAction) -> Unit
) {

    RunScaffold(
        withGradient = false,
        toolbar = {
            RunToolbar(
                showBackButton = true,
                title = stringResource(R.string.active_run),
                onBackCLick = {
                    onAction(ActiveRunAction.onBackClick)
                }
            )
        },

        floatingButton = {
            RunFloatingActionButton(
                icon = if (state.shouldTrack) StopIcon else StartIcon,
                onClick = {
                    onAction(ActiveRunAction.onToggleRunClick)
                },
                iconSize = 20.dp,
                contentDescription = if (state.shouldTrack) stringResource(R.string.pause_run) else stringResource(
                    R.string.start_run
                ),
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(paddingValues)
                .padding(12.dp)
        ) {
            RunDataCard(
                ellipseTime = state.elapsedTime,
                runData = state.runData
            )

        }

    }


}

@Preview
@Composable
private fun ActiveRunScreenPreview() {
    RunTheme {
        ActiveRunScreen(
            state = ActiveRunState(),
            onAction = {

            }
        )
    }
}