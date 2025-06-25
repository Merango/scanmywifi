package com.wifiscanner.permissions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class PermissionManagerTest {

    private lateinit var mockContext: Context
    private lateinit var mockActivity: FragmentActivity

    @Before
    fun setup() {
        mockContext = mockk(relaxed = true)
        mockActivity = mockk(relaxed = true)
        
        // Mock static methods
        mockkStatic(ContextCompat::checkSelfPermission)
    }

    @Test
    fun `hasLocationPermissions returns true when all permissions granted`() {
        // Arrange
        every { 
            ContextCompat.checkSelfPermission(
                mockContext, 
                Manifest.permission.ACCESS_FINE_LOCATION
            ) 
        } returns PackageManager.PERMISSION_GRANTED

        every { 
            ContextCompat.checkSelfPermission(
                mockContext, 
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) 
        } returns PackageManager.PERMISSION_GRANTED

        // Act
        val result = PermissionManager.hasLocationPermissions(mockContext)

        // Assert
        assertTrue(result)
    }

    @Test
    fun `hasLocationPermissions returns false when any permission not granted`() {
        // Arrange
        every { 
            ContextCompat.checkSelfPermission(
                mockContext, 
                Manifest.permission.ACCESS_FINE_LOCATION
            ) 
        } returns PackageManager.PERMISSION_DENIED

        every { 
            ContextCompat.checkSelfPermission(
                mockContext, 
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) 
        } returns PackageManager.PERMISSION_GRANTED

        // Act
        val result = PermissionManager.hasLocationPermissions(mockContext)

        // Assert
        assertFalse(result)
    }

    @Test
    fun `handlePermissionResult calls onPermissionGranted when all permissions granted`() {
        // Arrange
        val onGranted = mockk<() -> Unit>(relaxed = true)
        val onDenied = mockk<() -> Unit>(relaxed = true)

        // Act
        PermissionManager.handlePermissionResult(
            intArrayOf(PackageManager.PERMISSION_GRANTED, PackageManager.PERMISSION_GRANTED),
            onGranted,
            onDenied
        )

        // Assert
        verify(exactly = 1) { onGranted() }
        verify(exactly = 0) { onDenied() }
    }

    @Test
    fun `handlePermissionResult calls onPermissionDenied when any permission denied`() {
        // Arrange
        val onGranted = mockk<() -> Unit>(relaxed = true)
        val onDenied = mockk<() -> Unit>(relaxed = true)

        // Act
        PermissionManager.handlePermissionResult(
            intArrayOf(PackageManager.PERMISSION_GRANTED, PackageManager.PERMISSION_DENIED),
            onGranted,
            onDenied
        )

        // Assert
        verify(exactly = 0) { onGranted() }
        verify(exactly = 1) { onDenied() }
    }
}