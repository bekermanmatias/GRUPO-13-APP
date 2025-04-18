package com.example.app1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app1.R

@Composable
fun StartScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF6a11cb).copy(alpha = 0.9f),
                        Color(0xFF2575fc).copy(alpha = 0.9f)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Centra los elementos verticalmente
        ) {
            // Logo en la parte superior
            Image(
                painter = painterResource(id = R.drawable.ic_logo),  // Asegúrate de tener tu logo en res/drawable
                contentDescription = "App Logo",
                modifier = Modifier.size(180.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(80.dp))  // Espacio entre el logo y los botones

            // Botón de Iniciar sesión
            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF6a11cb)
                )
            ) {
                Text(
                    "Iniciar sesión",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))  // Espacio entre los botones

            // Botón de Registrarse
            Button(
                onClick = { navController.navigate("register") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF6a11cb)
                )
            ) {
                Text(
                    "Registrarse",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}
