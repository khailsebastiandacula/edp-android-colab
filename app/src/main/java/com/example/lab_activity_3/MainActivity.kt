package com.example.lab_activity_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lab_activity_3.ui.theme.ProfileTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            ProfileTheme {

                ProfileScreen()

            }

        }
    }
}