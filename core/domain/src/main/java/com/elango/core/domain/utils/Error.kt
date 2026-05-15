package com.elango.core.domain.utils


interface Error

sealed interface DataError : Error {
    enum class Network : DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        CONFLICT,
        UNAUTHORISED,
        SERVER_ERROR,
        PAYLOAD_TOO_LARGE,
        NO_INTERNET,
        UNKNOWN,
        SERIALIZATION,
    }

    enum class Local : DataError {
        DISK_FULL
    }
}
