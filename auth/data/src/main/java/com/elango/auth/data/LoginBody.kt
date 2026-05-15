package com.elango.auth.data

import kotlinx.serialization.Serializable


@Serializable
data class LoginRequest(
    val email: String, val password: String
)

@Serializable
data class LoginResponse(
    val accessToken: String, val refreshToken: String, val userId: String
)