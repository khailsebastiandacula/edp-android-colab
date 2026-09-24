package com.liceo.account.core

sealed interface AppResult<out T> {

    data class Success<T>(
        val data: T
    ) : AppResult<T>

    data object NoInternet : AppResult<Nothing>

    data object Timeout : AppResult<Nothing>

    data object WrongLogin : AppResult<Nothing>

    data object EmailTaken : AppResult<Nothing>

    data class Unknown(
        val msg: String
    ) : AppResult<Nothing>
}