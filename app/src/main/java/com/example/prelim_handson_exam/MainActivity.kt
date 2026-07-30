package com.example.prelim_handson_exam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Class
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.prelim_handson_exam.ui.theme.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileScreen()
                }
            }
        }
    }
}

@Composable
fun ProfileScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Avatar
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(
                    3.dp,
                    MaterialTheme.colorScheme.primary,
                    CircleShape
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Picture",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Name
        Text(
            text = "Khail Sebastian Dacula",
            style = MaterialTheme.typography.headlineSmall,
            color = NavyBlue
        )

        // Subtitle
        Text(
            text = "BSIT 3-A",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Information Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = DarkGray
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                InfoRow(
                    icon = Icons.Default.Person,
                    label = "Full Name",
                    value = "Khail Sebastian Dacula"
                )

                InfoRow(
                    icon = Icons.Default.School,
                    label = "Section",
                    value = "BSIT 3-1"
                )

                InfoRow(
                    icon = Icons.Default.Class,
                    label = "Course",
                    value = "Bachelor of Science in Information Technology"
                )

                InfoRow(
                    icon = Icons.Default.Phone,
                    label = "Mobile Number",
                    value = "09666906210"
                )

                InfoRow(
                    icon = Icons.Default.Email,
                    label = "Email Address",
                    value = "ksdacula52420@liceo.edu.ph"
                )

            }

        }

    }

}

@Composable
fun InfoRow(
    icon: ImageVector,
    label: String,
    value: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = RoyalBlue
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = RoyalBlue
            )

            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )

        }

    }

}

@Preview(
    showBackground = true,
    name = "Profile - Light"
)
@Composable
fun LightPreview() {
    MaterialTheme {
        ProfileScreen()
    }
}

@Preview(
    showBackground = true,
    name = "Profile - Dark"
)
@Composable
fun DarkPreview() {
    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
        ProfileScreen()
    }
}