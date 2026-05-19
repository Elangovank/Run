@file:OptIn(ExperimentalMaterial3Api::class)

package com.elango.run.presentation.run_overview

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elango.core.presentation.designsystem.AnalyticsIcon
import com.elango.core.presentation.designsystem.LogoIcon
import com.elango.core.presentation.designsystem.LogoutIcon
import com.elango.core.presentation.designsystem.RunIcon
import com.elango.core.presentation.designsystem.RunTheme
import com.elango.core.presentation.designsystem.components.RunFloatingActionButton
import com.elango.core.presentation.designsystem.components.RunScaffold
import com.elango.core.presentation.designsystem.components.RunToolbar
import com.elango.core.presentation.designsystem.components.uitls.DropDownItem
import com.elango.run.presentation.R
import org.koin.androidx.compose.koinViewModel


@Composable
fun RunOverviewAction(
    onStartClick: () -> Unit,
    viewModel: RunOverviewViewModel = koinViewModel()
) {
    RunOverviewScreen(
        onAction = { action ->
            when (action) {
                RunOverviewAction.onStartRunClick -> onStartClick()
                else -> Unit
            }

            viewModel.onAction(action)
        }
    )
}

@Composable
private fun RunOverviewScreen(
    onAction: (RunOverviewAction) -> Unit
) {

    var topBarState = rememberTopAppBarState()
    var scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topBarState
    )
    val menuItems = listOf(
        DropDownItem(
            icon = AnalyticsIcon,
            title = stringResource(R.string.analytics)
        ),
        DropDownItem(
            icon = LogoutIcon,
            title = stringResource(R.string.logout)
        )
    )
    RunScaffold(
        toolbar = {
            RunToolbar(
                showBackButton = false,
                title = stringResource(R.string.run),
                scrollBehavior = scrollBehavior,
                menuItems = menuItems,
                onMenuClick = { pos ->
                    when (pos) {
                        0 -> onAction(RunOverviewAction.onAnalyticsClick)
                        1 -> onAction(RunOverviewAction.onLogoutClick)
                    }
                },
                startContent = {
                    Icon(
                        imageVector = LogoIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(30.dp)
                    )
                }
            )
        },
        floatingButton = {
            RunFloatingActionButton(
                icon = RunIcon,
                iconSize = 30.dp,
                onClick = {
                    onAction(RunOverviewAction.onStartRunClick)
                }
            )
        }
    ) { paddingValues ->

    }

}

@Preview
@Composable
private fun RunOverviewActionScreenPreview() {
    RunTheme {
        RunOverviewScreen(
            onAction = {

            }
        )
    }
}
