package com.example.wifiscanner

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.button.MaterialButton
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun testMainActivityLayout() {
        // Launch the activity
        val scenario = ActivityScenario.launch(MainActivity::class.java)

        scenario.onActivity { activity ->
            // Check RecyclerView
            val recyclerView = activity.findViewById<RecyclerView>(R.id.rv_wifi_networks)
            assertNotNull("RecyclerView should not be null", recyclerView)
            assertSame(RecyclerView::class.java, recyclerView::class.java)

            // Check Scan Button
            val scanButton = activity.findViewById<MaterialButton>(R.id.btn_scan_wifi)
            assertNotNull("Scan button should not be null", scanButton)
            assertEquals("Scan Networks", scanButton.text)

            // Check Title TextView
            val titleView = activity.findViewById<View>(R.id.tv_app_title)
            assertNotNull("Title view should not be null", titleView)
        }
    }
}