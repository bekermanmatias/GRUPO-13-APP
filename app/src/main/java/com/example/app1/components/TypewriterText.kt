// en com/example/app1/components/TypewriterText.kt
package com.example.app1.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.app1.ui.theme.DarkText
import kotlinx.coroutines.delay

@Composable
fun TypewriterText(
    text: String,
    modifier: Modifier = Modifier,
    delayMillis: Long = 50L,
    textStyle: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.headlineLarge.copy(
        fontWeight = FontWeight.Bold,
        color = DarkText,
        fontSize = 26.sp
    ),
    textAlign: TextAlign = TextAlign.Center
) {
    var displayedText by remember { mutableStateOf("") }

    LaunchedEffect(text) {
        displayedText = ""
        for (i in text.indices) {
            displayedText += text[i]
            kotlinx.coroutines.delay(delayMillis)
        }
    }

    Text(
        text = displayedText,
        style = textStyle,
        textAlign = textAlign,
        modifier = modifier
    )
}

