package com.elango.auth.presentation.register

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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewmodel(
    val userDataValidator: UserDataValidator,
    val authRepository: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        state.email.textAsFlow()
            .onEach { email ->
                val isValidEmail = userDataValidator.isValidEmail(email)
                state = state.copy(
                    isEmailValid = isValidEmail,
                    canRegister = isValidEmail && state.passwordValidationState.isValidPassword
                )
            }.launchIn(viewModelScope)

        state.password.textAsFlow()
            .onEach { password ->
                val passwordValidationState = userDataValidator.validatePassword(password)
                state =
                    state.copy(
                        passwordValidationState = passwordValidationState,
                        canRegister = passwordValidationState.isValidPassword && state.isEmailValid
                    )
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: RegisterAction) {
        when (action) {
            RegisterAction.onRegisterClick -> {
                register()
            }

            RegisterAction.onTogglePasswordVisibilityClick -> {
                state = state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            }

            else -> {}
        }
    }

    fun register() {
        viewModelScope.launch {
            state = state.copy(isRegistering = true)
            val result = authRepository.register(
                state.email.text.toString(),
                state.password.text.toString()
            )
            state = state.copy(isRegistering = false)
            when (result) {
                is Result.Error -> {
                    if (result.error == DataError.Network.CONFLICT) {
                        eventChannel.send(
                            RegisterEvent.RegisterFailed(
                                UiText.StringResource(R.string.email_already_exist)
                            )
                        )
                    } else {
                        eventChannel.send(
                            RegisterEvent.RegisterFailed(
                                result.error.asUiText()
                            )
                        )
                    }

                }

                is Result.Success -> {
                    eventChannel.send(RegisterEvent.RegisterSuccess)
                }
            }
        }
    }
}