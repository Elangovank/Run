package com.elango.auth.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elango.auth.presentation.R
import com.elango.core.presentation.designsystem.EmailIcon
import com.elango.core.presentation.designsystem.Poppins
import com.elango.core.presentation.designsystem.RunTheme
import com.elango.core.presentation.designsystem.components.GradientBackground
import com.elango.core.presentation.designsystem.components.RunActionButton
import com.elango.core.presentation.designsystem.components.RunPasswordTextField
import com.elango.core.presentation.designsystem.components.RunTextField
import com.elango.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginScreenRoot(
    onLoginSuccess: () -> Unit,
    onSignUpClick: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val keyboardState = LocalSoftwareKeyboardController.current
    ObserveAsEvents(viewModel.event) { event ->
        when (event) {
            is LoginEvent.onError -> {
                keyboardState?.hide()
                Toast.makeText(
                    context,
                    event.msg.asString(context),
                    Toast.LENGTH_LONG
                ).show()
            }

            LoginEvent.onSuccess -> {
                keyboardState?.hide()
                Toast.makeText(
                    context,
                    R.string.login_success,
                    Toast.LENGTH_LONG
                ).show()
                onLoginSuccess.invoke()
            }
        }

    }
    LoginScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                LoginAction.onSignUpClick -> {
                    onSignUpClick.invoke()
                }

                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
private fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit
) {
    GradientBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(vertical = 32.dp)
                .padding(top = 12.dp)
        ) {
            Text(
                text = stringResource(R.string.hi_there),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = stringResource(R.string.welcome_to_run_description),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(48.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                RunTextField(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.emailState,
                    startIcon = EmailIcon,
                    endIcon = null,
                    error = stringResource(R.string.error_email),
                    additionalInfo = stringResource(R.string.error_email),
                    title = stringResource(R.string.title_email),
                    hint = stringResource(R.string.hint_email),
                    keyboardType = KeyboardType.Email
                )

                Spacer(modifier = Modifier.height(12.dp))

                RunPasswordTextField(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.passwordState,
                    title = stringResource(R.string.title_password),
                    hint = stringResource(R.string.hint_password),
                    isPasswordVisible = state.isPasswordVisible,
                    keyboardType = KeyboardType.Password,
                    onToggleClick = {
                        onAction(LoginAction.onPasswordToggle)
                    },
                )

                Spacer(modifier = Modifier.height(32.dp))

                RunActionButton(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = state.canLogin && !state.isLoggingIn,
                    onClick = {
                        onAction(LoginAction.onLoginClick)
                    },
                    text = stringResource(R.string.sign_in),
                    isLoading = state.isLoggingIn
                )
            }

            val dontHaveAccountText = stringResource(R.string.dont_have_an_account)
            val signUpText = stringResource(R.string.sign_up)
            val onSurfaceVariant = MaterialTheme.colorScheme.onSurfaceVariant
            val primaryColor = MaterialTheme.colorScheme.primary
            val annotatedString = remember(dontHaveAccountText, signUpText, onSurfaceVariant, primaryColor, onAction) {
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontFamily = Poppins,
                            color = onSurfaceVariant
                        )
                    ) {
                        append("$dontHaveAccountText ")
                    }
                    withLink(
                        LinkAnnotation.Clickable(
                            tag = "clickable_text",
                            linkInteractionListener = {
                                onAction(LoginAction.onSignUpClick)
                            }
                        )
                    ) {
                        withStyle(
                            style = SpanStyle(
                                fontFamily = Poppins,
                                color = primaryColor,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append(signUpText)
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = annotatedString,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    RunTheme {
        LoginScreen(
            state = LoginState(),
            onAction = {}
        )
    }
}
