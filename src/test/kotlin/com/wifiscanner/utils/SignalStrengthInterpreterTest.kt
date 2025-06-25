package com.wifiscanner.utils

import org.junit.Test
import org.junit.Assert.*

class SignalStrengthInterpreterTest {

    @Test
    fun `test signal strength interpretation`() {
        // Test Excellent signal strength
        assertEquals("Excellent (Strong)", SignalStrengthInterpreter.interpretSignalStrength(-40))
        
        // Test Good signal strength
        assertEquals("Good (Very Good)", SignalStrengthInterpreter.interpretSignalStrength(-55))
        
        // Test Fair signal strength
        assertEquals("Fair (Moderate)", SignalStrengthInterpreter.interpretSignalStrength(-65))
        
        // Test Weak signal strength
        assertEquals("Weak (Poor)", SignalStrengthInterpreter.interpretSignalStrength(-75))
        
        // Test Very Weak signal strength
        assertEquals("Very Weak (Unreliable)", SignalStrengthInterpreter.interpretSignalStrength(-90))
    }

    @Test
    fun `test signal strength color resources`() {
        // Test Excellent signal strength color
        assertEquals(android.R.color.holo_green_light, SignalStrengthInterpreter.getSignalStrengthColorRes(-40))
        
        // Test Good signal strength color
        assertEquals(android.R.color.holo_green_dark, SignalStrengthInterpreter.getSignalStrengthColorRes(-55))
        
        // Test Fair signal strength color
        assertEquals(android.R.color.holo_orange_light, SignalStrengthInterpreter.getSignalStrengthColorRes(-65))
        
        // Test Weak signal strength color
        assertEquals(android.R.color.holo_red_light, SignalStrengthInterpreter.getSignalStrengthColorRes(-75))
        
        // Test Very Weak signal strength color
        assertEquals(android.R.color.holo_red_dark, SignalStrengthInterpreter.getSignalStrengthColorRes(-90))
    }
}