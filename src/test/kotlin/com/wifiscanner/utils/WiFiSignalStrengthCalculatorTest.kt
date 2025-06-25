package com.wifiscanner.utils

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class WiFiSignalStrengthCalculatorTest {

    @Test
    fun `test signal strength percentage calculation`() {
        // Test boundary conditions
        assertEquals(0, WiFiSignalStrengthCalculator.calculateSignalStrengthPercentage(-100))
        assertEquals(100, WiFiSignalStrengthCalculator.calculateSignalStrengthPercentage(-50))
        
        // Test intermediate values
        assertEquals(50, WiFiSignalStrengthCalculator.calculateSignalStrengthPercentage(-75))
        
        // Test out-of-range values
        assertEquals(0, WiFiSignalStrengthCalculator.calculateSignalStrengthPercentage(-101))
        assertEquals(100, WiFiSignalStrengthCalculator.calculateSignalStrengthPercentage(-49))
    }

    @Test
    fun `test signal strength level determination`() {
        // Test signal strength levels
        assertEquals(
            WiFiSignalStrengthCalculator.SignalStrengthLevel.EXCELLENT,
            WiFiSignalStrengthCalculator.getSignalStrengthLevel(-50)
        )
        assertEquals(
            WiFiSignalStrengthCalculator.SignalStrengthLevel.GOOD,
            WiFiSignalStrengthCalculator.getSignalStrengthLevel(-55)
        )
        assertEquals(
            WiFiSignalStrengthCalculator.SignalStrengthLevel.FAIR,
            WiFiSignalStrengthCalculator.getSignalStrengthLevel(-65)
        )
        assertEquals(
            WiFiSignalStrengthCalculator.SignalStrengthLevel.WEAK,
            WiFiSignalStrengthCalculator.getSignalStrengthLevel(-80)
        )
    }
}