package com.elango.auth.presentation.intro

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elango.auth.presentation.R
import com.elango.core.presentation.designsystem.LogoIcon
import com.elango.core.presentation.designsystem.RunTheme
import com.elango.core.presentation.designsystem.components.GradientBackground
import com.elango.core.presentation.designsystem.components.RunActionButton
import com.elango.core.presentation.designsystem.components.RunOutLinedActionButton


@Composable
fun IntroScreenRoot(
    onSignUp: () -> Unit,
    onSignIn: () -> Unit
) {
    IntroScreen(
        onAction = { action ->
            when (action) {
                IntroAction.onSignInAction -> onSignIn()
                IntroAction.onSignUpAction -> onSignUp()
            }
        }
    )
}

@Composable
fun IntroScreen(
    onAction: (IntroAction) -> Unit
) {
    GradientBackground(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            LogoVertical()
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(bottom = 48.dp)
        ) {
            Text(
                text = stringResource(R.string.welcome_to_run),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.run_description),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(32.dp))
            RunOutLinedActionButton(
                enabled = true,
                onClick = {
                    onAction(IntroAction.onSignInAction)
                },
                text = stringResource(R.string.sign_in),
                isLoading = false
            )
            Spacer(modifier = Modifier.height(32.dp))
            RunActionButton(
                enabled = true,
                onClick = {
                    onAction(IntroAction.onSignUpAction)
                },
                text = stringResource(R.string.sign_up),
                isLoading = false
            )
        }

    }
}

@Composable
fun LogoVertical(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = LogoIcon,
            contentDescription = "Run Logo",
            tint = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.app_name),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview
@Composable
fun IntroScreenPreview() {
    RunTheme {
        IntroScreen(
            onAction = {}
        )
    }
}