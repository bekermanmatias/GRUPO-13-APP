// ui/theme/Color.kt
package com.example.app1.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

val PrimaryBlue = Color(0xFF0D47A0)
val SecondaryGray = Color(0xFFB0BEC5)
val BackgroundWhite = Color(0xFFFFFFFF)
val TextBlack = Color(0xFF000000)
val ErrorRed = Color(0xFFFF6B6B)

val Purple1 = Color(0xFF6A11CB)
val Blue1 = Color(0xFF2575FC)
val WhiteTransparent = Color(0x80FFFFFF)
val WhiteOpacity15 = Color(0x26FFFFFF)
val WhiteOpacity05 = Color(0x0DFFFFFF)

// Nuevos colores
val RosaPálido = Color(0xFFDAA0AF)
val AzulClaro = Color(0xFFA3D3EC)
val RosaSuave = Color(0xFFFFCDD2)
val GrisAzulado = Color(0xFFBBDEFB)
val DarkText = Color(0xFF24334D)
val AccentPink = Color(0xFFF47B8D)
val Error = Color(0xFFEA273E)




// Gradientes personalizados con nuevos colores
val LightGradient = Brush.verticalGradient(
    listOf(RosaSuave, GrisAzulado)
)

val DarkGradient = Brush.verticalGradient(
    listOf(Color(0xFF000428), Color(0xFF004e92))
)

@Preview(showBackground = true)
@Composable
fun PreviewLightGradient() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGradient)
    )
}


@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewDarkGradient() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGradient)
    )
}