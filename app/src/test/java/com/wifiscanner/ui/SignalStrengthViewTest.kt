package com.wifiscanner.ui

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SignalStrengthViewTest {

    private lateinit var context: Context
    private lateinit var signalStrengthView: SignalStrengthView

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        signalStrengthView = SignalStrengthView(context)
    }

    @Test
    fun `setSignalLevel should clamp value between 0 and 5`() {
        // Test lower bound
        signalStrengthView.setSignalLevel(-1)
        // Use reflection to check private signalLevel field
        val field = SignalStrengthView::class.java.getDeclaredField("signalLevel")
        field.isAccessible = true
        assertEquals(0, field.get(signalStrengthView))

        // Test upper bound
        signalStrengthView.setSignalLevel(6)
        assertEquals(5, field.get(signalStrengthView))

        // Test valid value
        signalStrengthView.setSignalLevel(3)
        assertEquals(3, field.get(signalStrengthView))
    }

    @Test
    fun `view should be created without crashing`() {
        assertNotNull(signalStrengthView)
    }
}