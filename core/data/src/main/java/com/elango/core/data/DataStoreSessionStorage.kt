package com.elango.core.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.elango.core.domain.AuthInfo
import com.elango.core.domain.SessionStorage
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json

class DataStoreSessionStorage(
    private val dataStore: DataStore<Preferences>
) : SessionStorage {

    override suspend fun get(): AuthInfo? {
        val json = dataStore.data.first()[KEY_AUTH_INFO]
        return json?.let {
            Json.decodeFromString<AuthInfoSerialize>(it).mapToAuthInfo()
        }
    }

    override suspend fun set(authInfo: AuthInfo?) {
        dataStore.edit { preferences ->
            if (authInfo == null) {
                preferences.remove(KEY_AUTH_INFO)
            } else {
                val json = Json.encodeToString(authInfo.mapToSerialization())
                preferences[KEY_AUTH_INFO] = json
            }
        }
    }

    companion object {
        private val KEY_AUTH_INFO = stringPreferencesKey("KEY_AUTH_INFO")
    }
}
