package com.liceo.account.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liceo.account.core.AppResult
import com.liceo.account.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: UserRepository = UserRepository()
) : ViewModel() {

    private val _state = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val state: StateFlow<AuthUiState> = _state.asStateFlow()

    private val dateRegex = Regex("""\d{4}-\d{2}-\d{2}""")

    fun clearMessage() {
        _state.value = AuthUiState.Idle
    }

    fun logout() {
        _state.value = AuthUiState.Idle
    }

    private fun messageFor(failure: AppResult<*>): String {
        return when (failure) {
            AppResult.NoInternet ->
                "No internet connection. Please try again."

            AppResult.Timeout ->
                "The server was too slow. Please try again."

            AppResult.WrongLogin ->
                "Wrong email or password."

            AppResult.EmailTaken ->
                "An account with this email already exists."

            is AppResult.Unknown ->
                "Something went wrong: ${failure.msg}"

            is AppResult.Success ->
                ""
        }
    }

    fun login(
        email: String,
        password: String
    ) {
        if (email.isBlank() || password.isBlank()) {
            _state.value = AuthUiState.Error(
                "Please enter your email and password."
            )
            return
        }

        if (!email.contains("@")) {
            _state.value = AuthUiState.Error(
                "Please enter a valid email."
            )
            return
        }

        _state.value = AuthUiState.Loading

        viewModelScope.launch {
            when (val result = repository.login(email, password)) {
                is AppResult.Success -> {
                    _state.value = AuthUiState.LoggedIn(result.data)
                }

                else -> {
                    _state.value = AuthUiState.Error(
                        messageFor(result)
                    )
                }
            }
        }
    }

    fun register(
        fullName: String,
        email: String,
        password: String,
        birthdate: String
    ) {
        if (
            fullName.isBlank() ||
            email.isBlank() ||
            password.isBlank() ||
            birthdate.isBlank()
        ) {
            _state.value = AuthUiState.Error(
                "Please fill in all four fields."
            )
            return
        }

        if (!email.contains("@")) {
            _state.value = AuthUiState.Error(
                "Please enter a valid email."
            )
            return
        }

        if (password.length < 6) {
            _state.value = AuthUiState.Error(
                "Password must be at least 6 characters."
            )
            return
        }

        if (!dateRegex.matches(birthdate)) {
            _state.value = AuthUiState.Error(
                "Birthdate must look like 2004-05-17."
            )
            return
        }

        _state.value = AuthUiState.Loading

        viewModelScope.launch {
            when (
                val result = repository.register(
                    fullName = fullName,
                    email = email,
                    password = password,
                    birthdate = birthdate
                )
            ) {
                is AppResult.Success -> {
                    _state.value = AuthUiState.AccountCreated(
                        result.data.fullName
                    )
                }

                else -> {
                    _state.value = AuthUiState.Error(
                        messageFor(result)
                    )
                }
            }
        }
    }
}