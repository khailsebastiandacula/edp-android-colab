package edu.liceo.fieldkit.ui

import android.Manifest
import android.util.Log
import androidx.camera.core.Camera
import androidx.camera.core.ImageCapture
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import edu.liceo.fieldkit.hardware.*
import edu.liceo.fieldkit.permissions.rememberPermission
import java.io.File

@Composable
fun CameraCard() {
    val context = LocalContext.current
    val camera = rememberPermission(Manifest.permission.CAMERA)
    val capture = remember { ImageCapture.Builder().build() }
    var photo by remember { mutableStateOf<File?>(null) }

    // TODO 14 State variables
    var cam by remember { mutableStateOf<Camera?>(null) }
    var torchOn by remember { mutableStateOf(false) }

    Card(Modifier.fillMaxWidth()) {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Field photo", style = MaterialTheme.typography.titleMedium)
            PermissionGate(
                state = camera,
                feature = "Camera",
                reason = "We need the camera to photograph the issue you report."
            ) {
                // TODO 9a: Live preview bound to ImageCapture (and TODO 14 callback)
                CameraPreview(
                    capture = capture,
                    modifier = Modifier.fillMaxWidth().height(240.dp),
                    onCameraReady = { cam = it }
                )

                // TODO 9b: Shutter button
                Button(onClick = {
                    takePhoto(context, capture) { saved -> photo = saved }
                }) {
                    Text("Take photo")
                }

                // TODO 9c: Display saved filename
                photo?.let { Text("Saved: ${it.name}") }

                // TODO 13: Shake capture listener
                val shake = rememberAccelerometer()
                var lastShot by remember { mutableLongStateOf(0L) }
                LaunchedEffect(shake) {
                    val now = System.currentTimeMillis()
                    // TODO 13a: Check for shake gesture with 1500ms cooldown
                    if (isShake(shake) && now - lastShot > 1500) {
                        // TODO 13b: Update lastShot and capture photo
                        lastShot = now
                        takePhoto(context, capture) { saved -> photo = saved }
                        // TODO 13c: Trigger haptic feedback and log statement
                        context.buzz()
                        Log.d("FieldKit", "Shake capture")
                    }
                }

                // TODO 14: Flashlight / Torch toggle button
                // TODO 14a: Check if flash unit exists on camera
                if (cam?.cameraInfo?.hasFlashUnit() == true) {
                    Button(onClick = {
                        // TODO 14b: Toggle torch state
                        torchOn = !torchOn
                        cam?.cameraControl?.enableTorch(torchOn)
                    }) {
                        // TODO 14c: Dynamic button text
                        Text(if (torchOn) "Torch off" else "Torch on")
                    }
                }
            }

            // GIVEN: Display thumbnail of last captured photo
            photo?.let { f ->
                val thumb = remember(f) { loadThumb(f) }
                thumb?.let {
                    Image(
                        bitmap = it,
                        contentDescription = "Last photo",
                        modifier = Modifier.size(96.dp)
                    )
                }
            }
        }
    }
}