package com.wifiscanner.utils

import android.content.Context
import android.net.wifi.WifiManager
import android.util.Log
import androidx.core.content.ContextCompat

/**
 * Utility class for managing WiFi manager initialization and related operations.
 */
class WiFiManagerUtil(private val context: Context) {

    private val TAG = "WiFiManagerUtil"
    private var wifiManager: WifiManager? = null

    /**
     * Initialize the WiFi manager system service.
     * @return WifiManager instance or null if not available
     */
    fun initializeWiFiManager(): WifiManager? {
        try {
            // Check if WiFi is supported on the device
            if (!isWiFiSupported()) {
                Log.w(TAG, "WiFi is not supported on this device")
                return null
            }

            // Get WiFi system service
            wifiManager = ContextCompat.getSystemService(context, WifiManager::class.java)
            
            // Additional checks for WiFi manager
            if (wifiManager == null) {
                Log.e(TAG, "Failed to retrieve WiFi system service")
                return null
            }

            return wifiManager
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing WiFi manager", e)
            return null
        }
    }

    /**
     * Check if WiFi is supported on the device.
     * @return true if WiFi is supported, false otherwise
     */
    fun isWiFiSupported(): Boolean {
        val packageManager = context.packageManager
        return packageManager.hasSystemFeature(android.content.pm.PackageManager.FEATURE_WIFI)
    }

    /**
     * Check if WiFi is currently enabled.
     * @return true if WiFi is enabled, false otherwise
     */
    fun isWiFiEnabled(): Boolean {
        return wifiManager?.isWifiEnabled == true
    }

    /**
     * Enable WiFi on the device.
     * @return true if WiFi was successfully enabled or already enabled, false otherwise
     */
    fun enableWiFi(): Boolean {
        return if (wifiManager != null) {
            if (!isWiFiEnabled()) {
                wifiManager?.isWifiEnabled = true
            }
            isWiFiEnabled()
        } else {
            false
        }
    }
}