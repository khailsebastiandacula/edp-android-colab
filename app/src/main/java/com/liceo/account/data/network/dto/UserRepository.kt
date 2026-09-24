package com.liceo.account.data

import com.liceo.account.core.AppResult
import com.liceo.account.data.network.NetworkModule
import com.liceo.account.data.network.UserApiService
import com.liceo.account.data.network.dto.NewUserDto
import com.liceo.account.data.network.dto.UserDto
import com.liceo.account.data.network.dto.toDomain
import com.liceo.account.domain.model.User
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class UserRepository(
    private val api: UserApiService = NetworkModule.api
) {

    private suspend fun findUsers(email: String): List<UserDto> {
        return try {
            api.findByEmail(email)
        } catch (e: HttpException) {
            if (e.code() == 404) {
                emptyList()
            } else {
                throw e
            }
        }
    }

    private suspend fun <T> safeCall(
        block: suspend () -> T
    ): AppResult<T> {
        return try {
            AppResult.Success(block())
        } catch (e: UnknownHostException) {
            AppResult.NoInternet
        } catch (e: SocketTimeoutException) {
            AppResult.Timeout
        } catch (e: HttpException) {
            AppResult.Unknown("Server error ${e.code()}")
        } catch (e: SerializationException) {
            AppResult.Unknown(
                "The server sent data we could not read."
            )
        } catch (e: IOException) {
            AppResult.NoInternet
        }
    }

    suspend fun login(
        email: String,
        password: String
    ): AppResult<User> {

        val result = safeCall {
            val matches = findUsers(email.trim())

            val found = matches.firstOrNull {
                it.email.equals(email.trim(), ignoreCase = true) &&
                        it.password == password
            }

            found
        }

        return when (result) {
            is AppResult.Success -> {
                val user = result.data

                if (user == null) {
                    AppResult.WrongLogin
                } else {
                    AppResult.Success(user.toDomain())
                }
            }

            is AppResult.NoInternet -> AppResult.NoInternet
            is AppResult.Timeout -> AppResult.Timeout
            is AppResult.WrongLogin -> AppResult.WrongLogin
            is AppResult.EmailTaken -> AppResult.EmailTaken
            is AppResult.Unknown -> AppResult.Unknown(result.msg)
        }
    }

    suspend fun register(
        fullName: String,
        email: String,
        password: String,
        birthdate: String
    ): AppResult<User> {

        val result = safeCall {
            val taken = findUsers(email.trim()).any {
                it.email.equals(email.trim(), ignoreCase = true)
            }

            if (taken) {
                null
            } else {
                val newUser = NewUserDto(
                    fullname = fullName.trim(),
                    email = email.trim(),
                    password = password,
                    birthdate = birthdate.trim()
                )

                api.createUser(newUser)
            }
        }

        return when (result) {
            is AppResult.Success -> {
                val user = result.data

                if (user == null) {
                    AppResult.EmailTaken
                } else {
                    AppResult.Success(user.toDomain())
                }
            }

            is AppResult.NoInternet -> AppResult.NoInternet
            is AppResult.Timeout -> AppResult.Timeout
            is AppResult.WrongLogin -> AppResult.WrongLogin
            is AppResult.EmailTaken -> AppResult.EmailTaken
            is AppResult.Unknown -> AppResult.Unknown(result.msg)
        }
    }
}