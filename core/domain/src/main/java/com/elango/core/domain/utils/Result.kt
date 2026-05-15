package com.elango.core.domain.utils


sealed interface Result<out D, out E : Error> {
    class Success<out D>(val data: D) : Result<D, Nothing>
    class Error<out E : com.elango.core.domain.utils.Error>(val error: E) : Result<Nothing, E>
}

inline fun <D, E : Error, R> Result<D, E>.map(map: (D) -> R): Result<R, E> {
    return when (this) {
        is Result.Success -> Result.Success(map(data))
        is Result.Error -> Result.Error(error)
    }
}

fun <D, E : Error> Result<D, E>.asEmptyDataResult(): EmptyDataResult<E> {
    return map { }
}

typealias EmptyDataResult<E> = Result<Unit, E>