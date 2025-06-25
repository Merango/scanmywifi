package com.wifiscanner.utils

/**
 * Utility class for interpreting WiFi signal strength levels.
 * Provides human-readable descriptions and categorical interpretations of signal strength.
 */
object SignalStrengthInterpreter {
    /**
     * Enum representing signal strength levels with their corresponding ranges and descriptions.
     *
     * @property minLevel The minimum signal level for this category
     * @property description A human-readable description of the signal strength
     */
    enum class SignalLevel(val minLevel: Int, val description: String) {
        EXCELLENT(75, "Excellent signal: Very strong and stable connection"),
        GOOD(50, "Good signal: Strong and reliable connection"),
        FAIR(25, "Fair signal: Moderate connection quality"),
        WEAK(0, "Weak signal: Poor connection, potential issues"),
        VERY_WEAK(Int.MIN_VALUE, "Very weak signal: Unreliable or no connection")
    }

    /**
     * Interprets the signal level based on the raw signal strength.
     *
     * @param signalStrength The raw signal strength value (typically -dBm)
     * @return SignalLevel enum representing the signal strength category
     */
    fun interpretSignalStrength(signalStrength: Int): SignalLevel {
        return SignalLevel.values()
            .sortedByDescending { it.minLevel }
            .first { signalStrength >= it.minLevel }
    }

    /**
     * Converts raw signal strength to a percentage for easier understanding.
     *
     * @param signalStrength The raw signal strength value
     * @return A percentage representation of signal strength (0-100)
     */
    fun signalStrengthToPercentage(signalStrength: Int): Int {
        return when {
            signalStrength >= 75 -> 100
            signalStrength >= 50 -> 75
            signalStrength >= 25 -> 50
            signalStrength >= 0 -> 25
            else -> 0
        }
    }
}