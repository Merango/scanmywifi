package com.wifiscanner.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SignalStrengthInterpreterTest {

    @Test
    fun `test signal strength interpretation`() {
        // Test excellent signal
        assertEquals(
            SignalStrengthInterpreter.SignalLevel.EXCELLENT,
            SignalStrengthInterpreter.interpretSignalStrength(80)
        )

        // Test good signal
        assertEquals(
            SignalStrengthInterpreter.SignalLevel.GOOD,
            SignalStrengthInterpreter.interpretSignalStrength(60)
        )

        // Test fair signal
        assertEquals(
            SignalStrengthInterpreter.SignalLevel.FAIR,
            SignalStrengthInterpreter.interpretSignalStrength(30)
        )

        // Test weak signal
        assertEquals(
            SignalStrengthInterpreter.SignalLevel.WEAK,
            SignalStrengthInterpreter.interpretSignalStrength(10)
        )

        // Test very weak signal
        assertEquals(
            SignalStrengthInterpreter.SignalLevel.VERY_WEAK,
            SignalStrengthInterpreter.interpretSignalStrength(-50)
        )
    }

    @Test
    fun `test signal strength to percentage conversion`() {
        // Test percentage conversions
        assertEquals(100, SignalStrengthInterpreter.signalStrengthToPercentage(80))
        assertEquals(75, SignalStrengthInterpreter.signalStrengthToPercentage(60))
        assertEquals(50, SignalStrengthInterpreter.signalStrengthToPercentage(40))
        assertEquals(25, SignalStrengthInterpreter.signalStrengthToPercentage(10))
        assertEquals(0, SignalStrengthInterpreter.signalStrengthToPercentage(-10))
    }

    @Test
    fun `test signal level descriptions`() {
        // Verify description presence and non-emptiness
        SignalStrengthInterpreter.SignalLevel.values().forEach { level ->
            assertNotNull(level.description)
            assertTrue(level.description.isNotBlank())
        }
    }
}