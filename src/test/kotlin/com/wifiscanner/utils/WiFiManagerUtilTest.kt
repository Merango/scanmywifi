package com.wifiscanner.utils

import android.content.Context
import android.net.wifi.WifiManager
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.*
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class WiFiManagerUtilTest {

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockWifiManager: WifiManager

    private lateinit var wifiManagerUtil: WiFiManagerUtil

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        wifiManagerUtil = WiFiManagerUtil(mockContext)
    }

    @Test
    fun `WiFi manager initialization should handle successful initialization`() {
        // Arrange
        `when`(mockContext.packageManager.hasSystemFeature(android.content.pm.PackageManager.FEATURE_WIFI))
            .thenReturn(true)

        // Act
        val result = wifiManagerUtil.initializeWiFiManager()

        // Assert
        assertNotNull(result)
    }

    @Test
    fun `WiFi manager initialization should handle unsupported device`() {
        // Arrange
        `when`(mockContext.packageManager.hasSystemFeature(android.content.pm.PackageManager.FEATURE_WIFI))
            .thenReturn(false)

        // Act
        val result = wifiManagerUtil.initializeWiFiManager()

        // Assert
        assertNull(result)
    }

    @Test
    fun `isWiFiSupported should return correct device support status`() {
        // Scenario 1: WiFi supported
        `when`(mockContext.packageManager.hasSystemFeature(android.content.pm.PackageManager.FEATURE_WIFI))
            .thenReturn(true)
        assertTrue(wifiManagerUtil.isWiFiSupported())

        // Scenario 2: WiFi not supported
        `when`(mockContext.packageManager.hasSystemFeature(android.content.pm.PackageManager.FEATURE_WIFI))
            .thenReturn(false)
        assertFalse(wifiManagerUtil.isWiFiSupported())
    }
}