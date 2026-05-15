package com.elango.core.data

import com.elango.core.domain.AuthInfo


fun AuthInfo.mapToSerialization() =
    AuthInfoSerialize(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId
    )

fun AuthInfoSerialize.mapToAuthInfo() = AuthInfo(
    accessToken = accessToken,
    refreshToken = refreshToken,
    userId = userId
)