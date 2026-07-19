package com.example.lab_activity_3

import androidx.compose.foundation.Image
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab_activity_3.ui.theme.ProfileTheme
import com.example.lab_activity_3.ui.theme.OnlineGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {

    Scaffold(

        topBar = {
            TopAppBar(

                title = {
                    Text(
                        "Profile",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },

                colors = TopAppBarDefaults.topAppBarColors(

                    containerColor = MaterialTheme.colorScheme.primary

                )

            )
        },

        floatingActionButton = {
            FloatingActionButton(

                onClick = {},

                containerColor = MaterialTheme.colorScheme.primary,

                contentColor = MaterialTheme.colorScheme.onPrimary

            ) {
                Text("+")
            }
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(20.dp),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {


            Box {

                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Profile Photo",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .background(
                            OnlineGreen,
                            CircleShape
                        )
                        .align(Alignment.BottomEnd)
                )

            }

        Text(
            text = "Khail Sebastian P. Dacula",
            style = MaterialTheme.typography.headlineMedium
        )


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { }
                ) {
                    Text("Follow")
                }

                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = { }
                ) {
                    Text("Message")
                }

            }


            Card(

                colors = CardDefaults.cardColors(

                    containerColor = MaterialTheme.colorScheme.surface

                ),

                elevation = CardDefaults.cardElevation(

                    defaultElevation = 8.dp

                )

            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),

                    horizontalArrangement = Arrangement.SpaceEvenly

                ) {

                    StatItem("40", "Posts")
                    StatItem("52.3K", "Followers")
                    StatItem("1,006", "Following")

                }

            }


            Card(

                colors = CardDefaults.cardColors(

                    containerColor = MaterialTheme.colorScheme.surface

                ),

                elevation = CardDefaults.cardElevation(

                    defaultElevation = 8.dp

                )

            ) {

                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Text(
                        "Contact Information",
                        style = MaterialTheme.typography.titleMedium
                    )

                    HorizontalDivider()

                    Text("📧 business.bastydy@gmail.com")
                    Text("📱 0966 690 6210")
                    Text("📍 Manila, Philippines")

                }

            }

        }

    }

}

@Composable
fun StatItem(
    value: String,
    label: String
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            value,
            style = MaterialTheme.typography.titleLarge
        )

        Text(label)

    }

}

@Preview(showBackground = true)
@Composable
fun LightPreview() {

    ProfileTheme {
        ProfileScreen()
    }

}

@Preview(showBackground = true)
@Composable
fun DarkPreview() {

    ProfileTheme(darkTheme = true) {
        ProfileScreen()
    }

}