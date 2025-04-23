// ui/theme/Theme.kt
package com.example.app1.ui.theme

import android.os.Build
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

private val lightColors = lightColorScheme(
    primary = Purple1,
    secondary = Blue1,
    background = BackgroundWhite,
    surface = WhiteOpacity15,
    surfaceVariant = WhiteTransparent,
    error = ErrorRed,
    onPrimary = BackgroundWhite,
    onSecondary = TextBlack,
    onBackground = TextBlack,
    onSurface = TextBlack,
    onError = BackgroundWhite
)

private val darkColors = darkColorScheme(
    primary = Purple1,
    secondary = Blue1,
    background = TextBlack,
    surface = WhiteOpacity05,
    surfaceVariant = WhiteTransparent,
    error = ErrorRed,
    onPrimary = BackgroundWhite,
    onSecondary = BackgroundWhite,
    onBackground = BackgroundWhite,
    onSurface = BackgroundWhite,
    onError = TextBlack
)

data class AppGradients(val background: Brush)

val LocalAppGradients = staticCompositionLocalOf {
    AppGradients(background = LightGradient)
}

@Composable
fun App1Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ->
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        darkTheme -> darkColors
        else -> lightColors
    }

    val gradients = if (darkTheme) AppGradients(background = DarkGradient)
    else AppGradients(background = LightGradient)

    CompositionLocalProvider(LocalAppGradients provides gradients) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppThemeLight() {
    App1Theme(darkTheme = false) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(LocalAppGradients.current.background)
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppThemeDark() {
    App1Theme(darkTheme = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(LocalAppGradients.current.background)
        )
    }
}
