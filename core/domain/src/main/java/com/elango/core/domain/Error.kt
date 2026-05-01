package com.elango.core.domain


interface Error

sealed interface DataError : Error {
    enum class Network : DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        CONFLICTS,
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
