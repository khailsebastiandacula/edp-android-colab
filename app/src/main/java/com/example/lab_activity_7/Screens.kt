package com.example.lab_activity_7

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val OrangeGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0xFFFF6F00),
        Color(0xFFFF9800),
        Color(0xFFFFCC80)
    )
)

@Composable
fun HomeScreen(
    onShowGreeting: (String) -> Unit
) {

    var name by remember {
        mutableStateOf("")
    }

    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(
            initialOffsetY = { it }
        ) + fadeIn()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(OrangeGradient)
                .padding(24.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Greeting App",
                fontSize = 34.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Jetpack Navigation Demo",
                fontSize = 18.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(40.dp))

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text("Enter your name")
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    onShowGreeting(name)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(15.dp)
            ) {

                Text(
                    text = "Show Greeting",
                    fontSize = 18.sp
                )

            }

        }

    }

}

@Composable
fun GreetingScreen(
    userName: String
) {

    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(
            initialOffsetY = { it }
        ) + fadeIn()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(OrangeGradient)
                .padding(24.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "👋",
                fontSize = 70.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Hello,",
                fontSize = 26.sp,
                color = Color.White
            )

            Text(
                text = userName,
                fontSize = 36.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Welcome to",
                fontSize = 22.sp,
                color = Color.White
            )

            Text(
                text = "Jetpack Navigation!",
                fontSize = 26.sp,
                color = Color.White
            )

        }

    }

}