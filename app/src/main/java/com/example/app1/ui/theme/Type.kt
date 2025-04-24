package com.example.app1.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.app1.R

// 1) Definís la familia de fuente
val JetBrainsMono = FontFamily(
    Font(R.font.jetbrains_mono_regular, FontWeight.Normal),
    Font(R.font.jetbrains_mono_bold,    FontWeight.Bold)
)

// 2) Lo usás en tu Typography
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = JetBrainsMono,
        fontWeight  = FontWeight.Bold,
        fontSize    = 26.sp,
        lineHeight  = 32.sp
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    labelSmall = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp
    )
)