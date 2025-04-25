package com.example.app1.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app1.R.drawable.logdef
import com.example.app1.ui.theme.*
import com.example.app1.components.TypewriterText // Asegurate de tener esta función implementada

@Composable
fun ContactScreen(navController: NavController) {
    val gradientBackground = LocalAppGradients.current.background

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
            .systemBarsPadding()
    ) {
        // Fondo desenfocado
        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(4.dp)
        )

        // Contenido de la pantalla
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = logdef),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(24.dp)),
                contentScale = ContentScale.Crop
            )


            TypewriterText(
                text = "Estamos para vos!",
                modifier = Modifier.padding(bottom = 8.dp, top = 8.dp),
                color = DarkText
            )

            Spacer(modifier = Modifier.height(1.dp))

            // Columna con fondo transparente
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.3f), RoundedCornerShape(24.dp))
                    .padding(24.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "📧 Email: soporte@app1.com",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = DarkText,
                        fontFamily = JetBrainsMono
                    )
                )
                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "📞 Teléfono: +54 9 11 1234-5678",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = DarkText,
                        fontFamily = JetBrainsMono
                    )
                )
                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "🌐 Redes: @app1.oficial",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = DarkText,
                        fontFamily = JetBrainsMono
                    )
                )

                Spacer(modifier = Modifier.height(160.dp))

                // Botón de volver
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AccentPink,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Volver",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewContactScreenLight() {
    App1Theme(darkTheme = false) {
        ContactScreen(navController = rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewContactScreenDark() {
    App1Theme(darkTheme = true) {
        ContactScreen(navController = rememberNavController())
    }
}
