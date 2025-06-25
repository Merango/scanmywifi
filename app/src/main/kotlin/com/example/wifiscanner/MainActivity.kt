package com.example.wifiscanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private lateinit var wifiNetworksRecyclerView: RecyclerView
    private lateinit var scanButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        wifiNetworksRecyclerView = findViewById(R.id.rv_wifi_networks)
        scanButton = findViewById(R.id.btn_scan_wifi)

        // Set up RecyclerView
        wifiNetworksRecyclerView.layoutManager = LinearLayoutManager(this)
        // TODO: Implement WiFi Network Adapter

        // Set up scan button click listener
        scanButton.setOnClickListener {
            // TODO: Implement WiFi scanning logic
        }
    }
}