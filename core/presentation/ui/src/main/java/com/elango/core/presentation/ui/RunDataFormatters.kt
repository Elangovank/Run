package com.elango.core.presentation.ui


import java.util.Locale
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.roundToInt
import kotlin.time.Duration


fun Duration.formatted(): String {
    val totalSeconds = inWholeSeconds
    val hours = String.format(locale = Locale.getDefault(), "%02d", totalSeconds / (60 * 60))
    val minutes = String.format(locale = Locale.getDefault(), "%02d", (totalSeconds % 3600) / 60)
    val seconds = String.format(locale = Locale.getDefault(), "%02d", totalSeconds % 60)
    return "$hours:$minutes:$seconds"
}

fun Double.toFormattedKM() = "${this.roundToDecimal(1)} km"


//How much time it takes to cover a certain distance
// here we're finding time to cover 1 km
fun Duration.toFormattedPace(distance: Double): String {

    if (this == Duration.ZERO || distance <= 0.0) {
        return "-"
    }

    val totalSeconds = this.inWholeSeconds
    val secondsPerKm = (totalSeconds / distance).roundToInt()
    val minutesPerKm = secondsPerKm / 60
    // find remaining seconds after calculating minutes
    val remainingSeconds = String.format(locale = Locale.getDefault(), "%02d", totalSeconds % 60)

    return "$minutesPerKm:$remainingSeconds / km"
}

// use case
// factor = 10f power 2 = 100
// eg: 3.678 = this * factor = > 367.8
// (this * factor) / factor => 367.8/100 => 3.67

fun Double.roundToDecimal(decimalCount: Int): Double {
    val factor = 10f.pow(decimalCount)
    return round(this * factor) / factor
}