package com.elango.auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elango.auth.domain.UserDataValidator
import com.elango.auth.domain.repository.AuthRepository
import com.elango.auth.presentation.R
import com.elango.core.domain.utils.DataError
import com.elango.core.domain.utils.Result
import com.elango.core.presentation.designsystem.textAsFlow
import com.elango.core.presentation.ui.UiText
import com.elango.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    val authRepository: AuthRepository,
    val userDataValidator: UserDataValidator
) : ViewModel() {

    private val eventChannel = Channel<LoginEvent>()
    val event = eventChannel.receiveAsFlow()


    var state by mutableStateOf(LoginState())
        private set

    init {
        combine(
            state.emailState.textAsFlow(),
            state.passwordState.textAsFlow()
        ) { email, password ->
            state = state.copy(
                canLogin = userDataValidator.isValidEmail(
                    email.trim()
                ) && password.isNotEmpty()
            )
        }.launchIn(viewModelScope)
    }

    fun onAction(action: LoginAction) {
        when (action) {
            LoginAction.onLoginClick -> login()
            LoginAction.onPasswordToggle -> {
                state = state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            }

            else -> Unit
        }

    }

    private fun login() {
        viewModelScope.launch {
            state = state.copy(isLoggingIn = true)
            val result = authRepository.login(
                email = state.emailState.text.toString(),
                password = state.passwordState.text.toString()
            )
            state = state.copy(isLoggingIn = false)

            when (result) {
                is Result.Error -> {
                    if (result.error == DataError.Network.UNAUTHORISED) {
                        eventChannel.send(LoginEvent.onError(msg = UiText.StringResource(R.string.error_email_or_password_incorrect)))
                    } else {
                        eventChannel.send(LoginEvent.onError(msg = result.error.asUiText()))
                    }

                }

                is Result.Success -> {
                    eventChannel.send(LoginEvent.onSuccess)
                }
            }
        }

    }
}