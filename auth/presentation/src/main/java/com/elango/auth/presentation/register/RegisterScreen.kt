package com.elango.auth.presentation.register

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elango.auth.domain.PasswordValidationState
import com.elango.auth.domain.UserDataValidator.Companion.MIN_PASSWORD_LENGTH
import com.elango.auth.presentation.R
import com.elango.core.presentation.designsystem.CheckIcon
import com.elango.core.presentation.designsystem.CrossIcon
import com.elango.core.presentation.designsystem.EmailIcon
import com.elango.core.presentation.designsystem.Poppins
import com.elango.core.presentation.designsystem.RunDarkRed
import com.elango.core.presentation.designsystem.RunGreen
import com.elango.core.presentation.designsystem.RunTheme
import com.elango.core.presentation.designsystem.components.GradientBackground
import com.elango.core.presentation.designsystem.components.RunActionButton
import com.elango.core.presentation.designsystem.components.RunPasswordTextField
import com.elango.core.presentation.designsystem.components.RunTextField
import com.elango.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreenRoot(
    onSignInClick: () -> Unit,
    onSuccessfulRegistration: () -> Unit,
    viewModel: RegisterViewmodel = koinViewModel()
) {
    val context: Context = LocalContext.current
    val keyboardState = LocalSoftwareKeyboardController.current
    ObserveAsEvents(flow = viewModel.events) {
        when (it) {
            is RegisterEvent.RegisterFailed -> {
                keyboardState?.hide()
                Toast.makeText(
                    context,
                    it.msg.asString(context),
                    Toast.LENGTH_LONG
                ).show()
            }

            RegisterEvent.RegisterSuccess -> {
                keyboardState?.hide()
                Toast.makeText(
                    context,
                    R.string.registration_success,
                    Toast.LENGTH_LONG
                ).show()
                onSuccessfulRegistration()
            }
        }
    }
    RegisterScreen(
        state = viewModel.state,
        onAction = { onAction ->
            when (onAction) {
                RegisterAction.onLoginClick -> onSignInClick.invoke()
                else -> Unit
            }
            viewModel.onAction(onAction)
        }
    )
}

@Composable
private fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit
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
                text = stringResource(R.string.create_account),
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(48.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                RunTextField(
                    state = state.email,
                    startIcon = EmailIcon,
                    endIcon = if (state.isEmailValid) CheckIcon else null,
                    error = stringResource(R.string.error_email),
                    hint = stringResource(R.string.hint_email),
                    title = stringResource(R.string.title_email),
                    additionalInfo = stringResource(R.string.error_email)
                )
                Spacer(modifier = Modifier.height(12.dp))
                RunPasswordTextField(
                    state = state.password,
                    hint = stringResource(R.string.hint_password),
                    title = stringResource(R.string.title_password),
                    isPasswordVisible = state.isPasswordVisible,
                    onToggleClick = {
                        onAction(RegisterAction.onTogglePasswordVisibilityClick)
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                PasswordRequirement(
                    text = stringResource(
                        id = R.string.atleast_x_character,
                        MIN_PASSWORD_LENGTH
                    ),
                    isValid = state.passwordValidationState.hasMinimumLength
                )
                Spacer(modifier = Modifier.height(16.dp))
                PasswordRequirement(
                    text = stringResource(
                        id = R.string.atleast_one_number
                    ),
                    isValid = state.passwordValidationState.hasNumber
                )
                Spacer(modifier = Modifier.height(16.dp))
                PasswordRequirement(
                    text = stringResource(
                        id = R.string.contain_lowercase_char
                    ),
                    isValid = state.passwordValidationState.hasLowerCaseCharacter
                )
                Spacer(modifier = Modifier.height(16.dp))
                PasswordRequirement(
                    text = stringResource(
                        id = R.string.contain_uppercase_char
                    ),
                    isValid = state.passwordValidationState.hasUpperCaseCharacter
                )
                Spacer(modifier = Modifier.height(32.dp))
                RunActionButton(
                    text = stringResource(R.string.sign_up),
                    isLoading = state.isRegistering,
                    enabled = state.canRegister,
                    onClick = {
                        onAction(RegisterAction.onRegisterClick)
                    }
                )
            }

            val alreadyHaveAccountText = stringResource(R.string.already_have_an_account)
            val signInText = stringResource(R.string.sign_in)
            val onSurfaceVariant = MaterialTheme.colorScheme.onSurfaceVariant
            val primaryColor = MaterialTheme.colorScheme.primary
            val annotatedString = remember(alreadyHaveAccountText, signInText, onSurfaceVariant, primaryColor, onAction) {
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontFamily = Poppins,
                            color = onSurfaceVariant
                        )
                    ) {
                        append("$alreadyHaveAccountText ")
                    }
                    withLink(
                        LinkAnnotation.Clickable(
                            tag = "clickable_text",
                            linkInteractionListener = {
                                onAction(RegisterAction.onLoginClick)
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
                            append(signInText)
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


@Composable
fun PasswordRequirement(
    modifier: Modifier = Modifier,
    text: String,
    isValid: Boolean
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (isValid) CheckIcon else CrossIcon, contentDescription = null,
            tint = if (isValid) RunGreen else RunDarkRed
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text, color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    RunTheme {
        RegisterScreen(
            state = RegisterState(
                passwordValidationState = PasswordValidationState(
                    hasNumber = true,
                    hasMinimumLength = true,
                    hasLowerCaseCharacter = true,
                    hasUpperCaseCharacter = true
                )
            ),
            onAction = { }
        )
    }
}
