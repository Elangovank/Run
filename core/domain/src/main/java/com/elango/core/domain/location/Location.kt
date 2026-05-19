package com.elango.core.domain.location

import java.time.LocalTime
import kotlin.time.Duration

data class Location(
    val lat: Double,
    val long: Double
)

data class LocationWithAltitude(
    val location: Location,
    val altitude: Double
)

data class LocationWithTimeStamp(
    val locationWithAltitude: LocationWithAltitude,
    val durationTimeStamp: Duration
)
