package com.liceo.account.ui

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LiceoAccountApp(
    viewModel: AuthViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    var screen by rememberSaveable {
        mutableStateOf("login")
    }

    when (screen) {

        "login" -> {
            LoginScreen(
                state = state,
                onLogin = { email, password ->
                    viewModel.login(email, password)
                },
                onCreateAccount = {
                    viewModel.clearMessage()
                    screen = "register"
                }
            )

            if (state is AuthUiState.LoggedIn) {
                screen = "profile"
            }
        }

        "register" -> {
            RegisterScreen(
                state = state,
                onRegister = { fullName, email, password, birthdate ->
                    viewModel.register(
                        fullName = fullName,
                        email = email,
                        password = password,
                        birthdate = birthdate
                    )
                },
                onBackToLogin = {
                    viewModel.clearMessage()
                    screen = "login"
                }
            )
        }

        "profile" -> {
            val loggedInState = state as? AuthUiState.LoggedIn

            if (loggedInState != null) {
                ProfileScreen(
                    user = loggedInState.user,
                    onLogout = {
                        viewModel.logout()
                        screen = "login"
                    }
                )
            } else {
                screen = "login"
            }
        }
    }
}