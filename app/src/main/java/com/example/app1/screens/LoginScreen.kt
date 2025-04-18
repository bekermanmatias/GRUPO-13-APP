package com.example.app1.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.compose.rememberNavController


@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }  // Estado para el nombre de usuario
    var password by remember { mutableStateOf("") }  // Estado para la contraseña
    var errorMessage by remember { mutableStateOf("") }  // Estado para mostrar errores

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Iniciar sesión", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(24.dp))

        // Campo para ingresar el nombre de usuario
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre de usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo para ingresar la contraseña
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),  // Oculta la contraseña
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para iniciar sesión
        Button(onClick = {
            // Validaciones de los campos de login
            if (username.isEmpty() || password.isEmpty()) {
                errorMessage = "Ambos campos son obligatorios"
            } else if (username == "Juan Torres" && password == "1234utn") {
                navController.navigate("welcome")  // Navega a la pantalla de bienvenida si es válido
            } else {
                errorMessage = "Usuario o contraseña incorrectos"
            }
        }) {
            Text("Iniciar sesión")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botón para navegar a la pantalla de registro
        Button(onClick = { navController.navigate("register") }) {
            Text("Registrarse")
        }

        // Mostrar mensaje de error si los campos son incorrectos
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    // Se debe crear una instancia de NavController, en este caso usamos rememberNavController para los previews
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}
