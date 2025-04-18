package com.example.app1.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


// Esquema de colores para el tema claro
private val lightColors = lightColorScheme(
    primary = Purple1,                 // Color púrpura personalizado
    secondary = Blue1,                 // Color azul personalizado
    background = BackgroundWhite,     // Fondo blanco
    surface = WhiteOpacity15,          // Blanco con opacidad 15% en el surface
    surfaceVariant = WhiteTransparent,  // Efecto vidrio
    error = ErrorRed,                  // Rojo para errores
    onPrimary = BackgroundWhite,       // Texto blanco sobre el color primario
    onSecondary = TextBlack,           // Texto negro sobre el color secundario
    onBackground = TextBlack,          // Texto negro sobre el fondo blanco
    onSurface = TextBlack,             // Texto negro sobre el surface
    onError = BackgroundWhite          // Texto blanco sobre el error
)

// Esquema de colores para el tema oscuro
private val darkColors = darkColorScheme(
    primary = Purple1,                 // Color púrpura personalizado
    secondary = Blue1,                 // Color azul personalizado
    background = TextBlack,           // Fondo negro para tema oscuro
    surface = WhiteOpacity05,          // Blanco con opacidad 5% en el surface para tema oscuro
    surfaceVariant = WhiteTransparent,  // Efecto vidrio
    error = ErrorRed,                  // Rojo para errores
    onPrimary = BackgroundWhite,       // Texto blanco sobre el color primario
    onSecondary = BackgroundWhite,     // Texto blanco sobre el color secundario
    onBackground = BackgroundWhite,    // Texto blanco sobre el fondo oscuro
    onSurface = BackgroundWhite,       // Texto blanco sobre el surface oscuro
    onError = TextBlack                // Texto negro sobre el error
)


@Composable
fun App1Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColors
        else -> lightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}