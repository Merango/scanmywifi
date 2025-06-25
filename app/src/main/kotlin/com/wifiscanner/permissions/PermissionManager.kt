package com.wifiscanner.permissions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

class PermissionManager {

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
        private val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        /**
         * Check if location permissions are granted
         * @param context Application context
         * @return Boolean indicating if location permissions are granted
         */
        fun hasLocationPermissions(context: Context): Boolean {
            return REQUIRED_PERMISSIONS.all { permission ->
                ContextCompat.checkSelfPermission(
                    context,
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            }
        }

        /**
         * Request location permissions at runtime
         * @param activity The current FragmentActivity
         * @param onPermissionGranted Callback for when permissions are granted
         * @param onPermissionDenied Callback for when permissions are denied
         */
        fun requestLocationPermissions(
            activity: FragmentActivity,
            onPermissionGranted: () -> Unit,
            onPermissionDenied: () -> Unit
        ) {
            // Check if permissions are already granted
            if (hasLocationPermissions(activity)) {
                onPermissionGranted()
                return
            }

            // Request permissions
            ActivityCompat.requestPermissions(
                activity,
                REQUIRED_PERMISSIONS,
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }

        /**
         * Handle permission request results
         * @param grantResults The grant results for the requested permissions
         * @param onPermissionGranted Callback for when permissions are granted
         * @param onPermissionDenied Callback for when permissions are denied
         */
        fun handlePermissionResult(
            grantResults: IntArray,
            onPermissionGranted: () -> Unit,
            onPermissionDenied: () -> Unit
        ) {
            if (grantResults.isNotEmpty() && 
                grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                onPermissionGranted()
            } else {
                onPermissionDenied()
            }
        }
    }
}