package com.example.lab6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.IntentFilter
import android.location.LocationManager
import com.example.lab6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private lateinit var _locationManager: LocationManager
    private lateinit var _gpsStatusReceiver: GpsStatusReceiver
    private var _listenGpsStatus: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        _binding.gpsCheckBox.setOnCheckedChangeListener { _, isChecked ->
            _listenGpsStatus = isChecked
        }

        _gpsStatusReceiver = GpsStatusReceiver(
            _locationManager,
            { _listenGpsStatus }
        )

        val filter = IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
        registerReceiver(_gpsStatusReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(_gpsStatusReceiver)
    }
}