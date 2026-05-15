package com.elango.auth.domain.repository

import com.elango.core.domain.utils.DataError
import com.elango.core.domain.utils.EmptyDataResult

interface AuthRepository {

    suspend fun login(email: String, password: String): EmptyDataResult<DataError.Network>
    suspend fun register(email: String, password: String): EmptyDataResult<DataError.Network>
}