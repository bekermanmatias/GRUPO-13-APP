package com.example.app1.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ContactScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Contacto",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "📧 Email: soporte@app1.com")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "📞 Teléfono: +54 9 11 1234-5678")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "🌐 Instagram: @app1.oficial")

        Spacer(modifier = Modifier.height(48.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}
