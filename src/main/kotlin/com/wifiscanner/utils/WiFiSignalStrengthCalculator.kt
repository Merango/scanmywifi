package com.wifiscanner.utils

/**
 * Utility class for calculating WiFi signal strength metrics.
 */
object WiFiSignalStrengthCalculator {
    // Signal strength thresholds for different levels
    private const val EXCELLENT_THRESHOLD = -50
    private const val GOOD_THRESHOLD = -60
    private const val FAIR_THRESHOLD = -70

    /**
     * Calculates signal strength percentage from RSSI value.
     * 
     * @param rssi Received Signal Strength Indicator (dBm)
     * @return Signal strength percentage (0-100)
     */
    fun calculateSignalStrengthPercentage(rssi: Int): Int {
        return when {
            rssi >= EXCELLENT_THRESHOLD -> 100
            rssi <= -100 -> 0
            else -> {
                // Linear interpolation between -100 and -50 dBm
                val normalizedRssi = rssi.coerceIn(-100, -50)
                ((normalizedRssi + 100) * 2).coerceIn(0, 100)
            }
        }
    }

    /**
     * Determines signal strength level based on RSSI value.
     * 
     * @param rssi Received Signal Strength Indicator (dBm)
     * @return Signal strength level as a descriptive string
     */
    fun getSignalStrengthLevel(rssi: Int): SignalStrengthLevel {
        return when {
            rssi >= EXCELLENT_THRESHOLD -> SignalStrengthLevel.EXCELLENT
            rssi >= GOOD_THRESHOLD -> SignalStrengthLevel.GOOD
            rssi >= FAIR_THRESHOLD -> SignalStrengthLevel.FAIR
            else -> SignalStrengthLevel.WEAK
        }
    }

    /**
     * Enum representing different signal strength levels.
     */
    enum class SignalStrengthLevel {
        EXCELLENT, GOOD, FAIR, WEAK
    }
}