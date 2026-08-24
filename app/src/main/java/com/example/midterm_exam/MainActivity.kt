package com.example.midterm_exam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.midterm_exam.RecipeApp

// Modern Culinary Palette
val WarmTerracotta = Color(0xFFC85A32)
val OnTerracotta = Color(0xFFFFFFFF)
val TerracottaContainer = Color(0xFFFBEBE6)
val OnTerracottaContainer = Color(0xFF3E1305)

val SageOlive = Color(0xFF556B2F)
val SoftCreamBg = Color(0xFFFAF7F2)
val SurfaceWarm = Color(0xFFFFFFFF)
val DarkCharcoal = Color(0xFF23211E)
val CharcoalMuted = Color(0xFF635F59)
val DividerColor = Color(0xFFEAE5DC)

private val CulinaryColorScheme = lightColorScheme(
    primary = WarmTerracotta,
    onPrimary = OnTerracotta,
    primaryContainer = TerracottaContainer,
    onPrimaryContainer = OnTerracottaContainer,
    secondary = SageOlive,
    background = SoftCreamBg,
    surface = SurfaceWarm,
    onBackground = DarkCharcoal,
    onSurface = DarkCharcoal,
    onSurfaceVariant = CharcoalMuted,
    outlineVariant = DividerColor
)

@Composable
fun RecipeBookTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = CulinaryColorScheme,
        shapes = Shapes(
            small = RoundedCornerShape(12.dp),
            medium = RoundedCornerShape(16.dp),
            large = RoundedCornerShape(24.dp)
        ),
        content = content
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeBookTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipeApp()
                }
            }
        }
    }
}