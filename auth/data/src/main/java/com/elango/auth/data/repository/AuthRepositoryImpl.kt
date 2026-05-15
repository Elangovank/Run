package com.elango.auth.data.repository

import com.elango.auth.data.LoginRequest
import com.elango.auth.data.LoginResponse
import com.elango.auth.data.model.RegisterRequest
import com.elango.auth.domain.repository.AuthRepository
import com.elango.core.data.networking.post
import com.elango.core.domain.AuthInfo
import com.elango.core.domain.SessionStorage
import com.elango.core.domain.utils.DataError
import com.elango.core.domain.utils.EmptyDataResult
import com.elango.core.domain.utils.Result
import com.elango.core.domain.utils.asEmptyDataResult
import io.ktor.client.HttpClient


class AuthRepositoryImpl(
    val httpClient: HttpClient,
    val sessionStorage: SessionStorage
) : AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): EmptyDataResult<DataError.Network> {

        val result = httpClient.post<LoginRequest, LoginResponse>(
            route = "/login",
            body = LoginRequest(email = email, password = password)
        )

        if (result is Result.Success) {
            sessionStorage.set(
                AuthInfo(
                    accessToken = result.data.accessToken,
                    refreshToken = result.data.refreshToken,
                    userId = result.data.userId
                )
            )
        }

        return result.asEmptyDataResult()

    }

    override suspend fun register(
        email: String,
        password: String
    ): EmptyDataResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(email = email, password = password)
        )
    }
}