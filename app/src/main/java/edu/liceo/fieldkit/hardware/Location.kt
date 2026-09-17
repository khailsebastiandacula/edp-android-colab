package edu.liceo.fieldkit.hardware

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

@SuppressLint("MissingPermission")   // GIVEN: LocationCard checks the permission first
fun Context.currentLocation(onResult: (Location?) -> Unit) {
    val client = LocationServices.getFusedLocationProviderClient(this)

    // TODO 10a: Request single high-accuracy location update
    client.getCurrentLocation(
        Priority.PRIORITY_HIGH_ACCURACY,
        CancellationTokenSource().token
    )
        // TODO 10b: Deliver result on success (may be null if disabled/no fix)
        .addOnSuccessListener { loc ->
            onResult(loc)
        }
        // TODO 10c: Deliver null on failure
        .addOnFailureListener {
            onResult(null)
        }
}