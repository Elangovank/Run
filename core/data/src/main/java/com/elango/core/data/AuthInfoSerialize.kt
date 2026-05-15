package com.elango.core.data

import kotlinx.serialization.Serializable

@Serializable
data class AuthInfoSerialize(
    val accessToken: String,
    val refreshToken: String,
    val userId: String
)
