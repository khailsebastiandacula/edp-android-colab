package com.example.lab_activity_3.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(

    primary = Primary,
    onPrimary = OnPrimary,

    primaryContainer = PrimaryContainer,

    secondary = Secondary,

    surface = Surface,

    background = Color.White,

    onBackground = Color.Black,

    onSurfaceVariant = OnSurfaceVariant


)

private val DarkColors = darkColorScheme(

    primary = PrimaryDark,
    onPrimary = OnPrimaryDark,

    primaryContainer = PrimaryContainerDark,

    secondary = SecondaryDark,

    surface = SurfaceDark,

    background = Color.Black,

    onBackground = Color.White,

    onSurfaceVariant = OnSurfaceVariantDark

)

@Composable
fun ProfileTheme(

    darkTheme: Boolean = isSystemInDarkTheme(),

    content: @Composable () -> Unit

) {

    val colors =
        if (darkTheme) DarkColors
        else LightColors

    MaterialTheme(

        colorScheme = colors,

        typography = Typography,

        shapes = Shapes,

        content = content

    )

}
