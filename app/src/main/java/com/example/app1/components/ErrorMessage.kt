package com.example.app1.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app1.ui.theme.Error

@Composable
fun ErrorMessage(message: String, modifier: Modifier = Modifier) {
    AnimatedVisibility(
        visible = message.isNotBlank(),
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Error.copy(alpha = 0.1f))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ErrorOutline,
                contentDescription = "Error",
                tint = Error,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = message,
                color = Error,
                fontSize = 14.sp
            )
        }
    }
}
