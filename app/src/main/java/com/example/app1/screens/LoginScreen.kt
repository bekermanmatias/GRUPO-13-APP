package com.example.app1.screens

import android.content.res.Configuration
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app1.R.drawable.logdef
import com.example.app1.ui.theme.AccentPink
import com.example.app1.ui.theme.App1Theme
import com.example.app1.ui.theme.DarkText
import com.example.app1.ui.theme.Error
import com.example.app1.ui.theme.LocalAppGradients

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf("") }

    val gradientBackground = LocalAppGradients.current.background

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
            .systemBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(4.dp)
        )

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

            Text(
                text = "Bienvenid@!",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = DarkText,
                    fontSize = 26.sp
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
            )

            // Columna para los campos de entrada y el mensaje de error
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.3f), RoundedCornerShape(24.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp) // Esto mantiene el espacio entre los elementos
            ) {
                // Reservar espacio para el mensaje de error dentro del recuadro
                if (errorMessage.isNotBlank()) {
                    Text(
                        text = errorMessage,
                        color = Error,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(bottom = 0.1.dp), // Espacio entre el error y el siguiente campo
                        textAlign = TextAlign.Center
                    )
                }

                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Nombre de usuario", color = DarkText.copy(alpha = 0.8f)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White.copy(alpha = 0.3f),
                        focusedContainerColor = Color.White.copy(alpha = 0.3f),
                        unfocusedTextColor = DarkText,
                        focusedTextColor = DarkText,
                        cursorColor = DarkText,
                        unfocusedIndicatorColor = DarkText.copy(alpha = 0.5f),
                        focusedIndicatorColor = DarkText
                    )
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña", color = DarkText.copy(alpha = 0.8f)) },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White.copy(alpha = 0.3f),
                        focusedContainerColor = Color.White.copy(alpha = 0.3f),
                        unfocusedTextColor = DarkText,
                        focusedTextColor = DarkText,
                        cursorColor = DarkText,
                        unfocusedIndicatorColor = DarkText.copy(alpha = 0.5f),
                        focusedIndicatorColor = DarkText
                    )
                )

                Spacer(modifier = Modifier.height(6.dp))

                Button(
                    onClick = {
                        errorMessage = when {
                            username.text.isBlank() || password.text.isBlank() -> "Por favor, completá ambos campos."
                            username.text != "Juan Torres" || password.text != "1234utn" -> "Usuario o contraseña incorrectos."
                            else -> {
                                navController.navigate("welcome")
                                ""
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AccentPink,
                        contentColor = Color.White
                    )
                ) {
                    Text("Iniciar sesión", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(6.dp))

                OutlinedButton(
                    onClick = {
                        navController.navigate("register")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = DarkText,
                    ),
                    border = ButtonDefaults.outlinedButtonBorder
                ) {
                    Text("Registrarse", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Botón de contacto
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    TextButton(onClick = {
                        navController.navigate("contact")
                    }) {
                        Text("¿Necesitás ayuda? Contacto", color = DarkText.copy(alpha = 0.9f))
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLoginScreenLight() {
    App1Theme(darkTheme = false) {
        LoginScreen(navController = rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewLoginScreenDark() {
    App1Theme(darkTheme = true) {
        LoginScreen(navController = rememberNavController())
    }
}
