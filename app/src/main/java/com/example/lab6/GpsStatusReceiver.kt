package com.example.lab6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.widget.Toast

class GpsStatusReceiver(
    private val locationManager: LocationManager,
    private val listenGpsStatus: () -> Boolean
) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (listenGpsStatus()) {
            if (gpsEnabled) {
                showToast(context, "GPS увімкнено")
            } else {
                showToast(context, "GPS вимкнено")
            }
        }
    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}