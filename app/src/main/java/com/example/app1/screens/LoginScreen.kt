package com.example.app1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import com.example.app1.R

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf("") }

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
            .systemBarsPadding()
    ) {
        // Fondo difuminado
        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(4.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(24.dp)) // Aplica el redondeo
                    .background(White.copy(alpha = 0.1f)), // Fondo opcional
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Iniciar sesión",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = White,
                    fontSize = 28.sp
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // FORMULARIO
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White.copy(alpha = 0.15f), RoundedCornerShape(24.dp))
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Nombre de usuario", color = White.copy(alpha = 0.8f)) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(White.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp)),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedTextColor = White,
                        focusedTextColor = White,
                        cursorColor = White,
                        unfocusedIndicatorColor = White.copy(alpha = 0.5f),
                        focusedIndicatorColor = White
                    )
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña", color = White.copy(alpha = 0.8f)) },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(White.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp)),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedTextColor = White,
                        focusedTextColor = White,
                        cursorColor = White,
                        unfocusedIndicatorColor = White.copy(alpha = 0.5f),
                        focusedIndicatorColor = White
                    )
                )

                Button(
                    onClick = {
                        errorMessage = when {
                            username.text.isBlank() || password.text.isBlank() -> {
                                "Por favor, completá ambos campos."
                            }
                            username.text != "Juan Torres" || password.text != "1234utn" -> {
                                "Usuario o contraseña incorrectos."
                            }
                            else -> {
                                navController.navigate("welcome")
                                ""
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = White,
                        contentColor = Color(0xFF6a11cb)
                    )
                ) {
                    Text("Iniciar sesión", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }

                // Enlaces inferiores
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Registrarse
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = White.copy(alpha = 0.15f)
                    ) {
                        TextButton(
                            onClick = { navController.navigate("register") },
                            modifier = Modifier.padding(horizontal = 12.dp)
                        ) {
                            Text(
                                "Registrarse",
                                color = White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Ayuda
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = White.copy(alpha = 0.1f)
                    ) {
                        TextButton(
                            onClick = { navController.navigate("contact") },
                            modifier = Modifier.padding(horizontal = 12.dp)
                        ) {
                            Text(
                                "Ayuda",
                                color = White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }


                // Mensaje de error
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}
