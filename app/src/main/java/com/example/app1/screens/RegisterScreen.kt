package com.example.app1.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app1.R.drawable.logdef
import com.example.app1.components.ErrorMessage
import com.example.app1.components.TypewriterText
import com.example.app1.ui.theme.AccentPink
import com.example.app1.ui.theme.App1Theme
import com.example.app1.ui.theme.DarkText
import com.example.app1.ui.theme.Error
import com.example.app1.ui.theme.LocalAppGradients

@Composable
fun RegisterScreen(navController: NavController) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    // Variable para habilitar/deshabilitar el botón
    val isButtonEnabled = remember(name, email, password, confirmPassword) {
        name.text.isNotBlank() &&
                email.text.isNotBlank() &&
                password.text.isNotBlank() &&
                confirmPassword.text.isNotBlank() &&
                isValidEmail(email.text) &&
                password.text.length >= 6 &&
                password.text == confirmPassword.text
    }

    val roundedShape = RoundedCornerShape(12.dp)
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
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 11.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = logdef),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(140.dp)
                        .offset(x = (-16).dp)
                        .clip(RoundedCornerShape(24.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                TypewriterText(
                    text = "¡Creemos tu nueva cuenta! ",
                    modifier = Modifier.padding(bottom = 8.dp, top = 8.dp),
                    color = DarkText,
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.3f), RoundedCornerShape(24.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                ErrorMessage(message = errorMessage)

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre", color = DarkText.copy(alpha = 0.8f)) },
                    singleLine = true,
                    shape = roundedShape,
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
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email", color = DarkText.copy(alpha = 0.8f)) },
                    singleLine = true,
                    shape = roundedShape,
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
                    shape = roundedShape,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = "Toggle Password",
                                tint = DarkText
                            )
                        }
                    },
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
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Repetir contraseña", color = DarkText.copy(alpha = 0.8f)) },
                    singleLine = true,
                    shape = roundedShape,
                    visualTransformation = if (confirmVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { confirmVisible = !confirmVisible }) {
                            Icon(
                                imageVector = if (confirmVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = "Toggle Confirm",
                                tint = DarkText
                            )
                        }
                    },
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

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        errorMessage = when {
                            name.text.isBlank() || email.text.isBlank() || password.text.isBlank() || confirmPassword.text.isBlank() ->
                                "Por favor completá todos los campos."
                            !isValidEmail(email.text) ->
                                "El email no es válido."
                            password.text.length < 6 ->
                                "La contraseña debe tener al menos 6 caracteres."
                            password.text != confirmPassword.text ->
                                "Las contraseñas no coinciden."
                            else -> {
                                navController.navigate("Login")
                                ""
                            }
                        }
                    },
                    enabled = isButtonEnabled, // Botón habilitado solo si todos los campos son válidos
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AccentPink,
                        contentColor = Color.White
                    )
                ) {
                    Text("Registrarse", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(0.1.dp))

                OutlinedButton(
                    onClick = { navController.navigate("login") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = ButtonDefaults.outlinedButtonBorder(enabled = true),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = DarkText
                    )
                ) {
                    Text("Ya tengo cuenta", fontSize = 16.sp)
                }

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    TextButton(onClick = { navController.navigate("contact") }) {
                        Text("¿Necesitás ayuda? Contacto", color = DarkText.copy(alpha = 0.9f))
                    }
                }
            }
        }
    }
}

fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}


@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreenLight() {
    App1Theme(darkTheme = false) {
        RegisterScreen(navController = rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewRegisterScreenDark() {
    App1Theme(darkTheme = true) {
        RegisterScreen(navController = rememberNavController())
    }
}