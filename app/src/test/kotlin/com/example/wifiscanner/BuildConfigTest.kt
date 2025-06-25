package com.example.wifiscanner

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class BuildConfigTest {
    @Test
    fun `verify build configuration`() {
        // Verify basic build configuration parameters
        assertEquals("com.example.wifiscanner", BuildConfig.APPLICATION_ID)
        assertEquals(1, BuildConfig.VERSION_CODE)
        assertEquals("1.0.0", BuildConfig.VERSION_NAME)
        
        // Ensure compileSdk and targetSdk are set correctly
        assert(BuildConfig.COMPILE_SDK_VERSION >= 29) { "Minimum Android 10 support required" }
    }

    @Test
    fun `verify signing configuration`() {
        // In a real-world scenario, you'd add more robust keystore validation
        assertNotNull("Signing config should be set", BuildConfig.RELEASE_KEYSTORE_FILE)
    }
}