package edu.liceo.fieldkit.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import edu.liceo.fieldkit.permissions.*

@Composable
fun PermissionGate(
    state: PermissionState,
    feature: String,                 // e.g., "Camera"
    reason: String,                  // Why the app needs it
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        when (state.status) {
            // TODO 4a: Display protected UI content when permission is granted
            PermStatus.Granted -> content()

            // TODO 4b: Initial state; prompt user to grant permission
            PermStatus.NotAsked -> {
                Button(onClick = state.request) {
                    Text("Allow $feature")
                }
            }

            // TODO 4c: Denied once; explain rationale and offer retry
            PermStatus.NeedsRationale -> {
                Text(reason)
                Button(onClick = state.request) {
                    Text("Try again")
                }
            }

            // TODO 4d: Permanently denied; direct user to system app settings
            PermStatus.Denied -> {
                Text("$feature is blocked. Turn it on in Settings.")
                Button(onClick = { context.openAppSettings() }) {
                    Text("Open Settings")
                }
            }
        }
    }
}