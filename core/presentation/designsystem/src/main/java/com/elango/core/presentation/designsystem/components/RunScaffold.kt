package com.elango.core.presentation.designsystem.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.elango.core.presentation.designsystem.RunTheme

@Composable
fun RunScaffold(
    modifier: Modifier = Modifier,
    withGradient: Boolean = true,
    toolbar: @Composable () -> Unit = {},
    floatingButton: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = toolbar,
        floatingActionButton = floatingButton,
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        if (withGradient) {
            GradientBackground() {
                content(paddingValues)
            }
        } else {
            content(paddingValues)
        }
    }
}

@Preview
@Composable
private fun RunScaffoldPreview() {
    RunTheme {
        RunScaffold(content = {

        })
    }
}