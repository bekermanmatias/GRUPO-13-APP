package com.example.app1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow



import com.example.app1.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WelcomeScreen(navController: NavController) {
    var selectedPlatform by remember { mutableStateOf("Android") }
    val preferences = remember {
        mutableStateMapOf(
            "Programación" to false,
            "Redes" to false,
            "Seguridad" to false,
            "Hardware" to false,
            "Otra" to false
        )
    }
    var otherPreference by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Bienvenido a la aplicación Juan Torres",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Seleccioná tu plataforma:")

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),  // Asegura que el Row ocupe todo el ancho
            horizontalArrangement = Arrangement.Center,  // Centra las imágenes horizontalmente
            verticalAlignment = Alignment.CenterVertically  // Centra las imágenes verticalmente
        ) {
            // Imagen de Android
            Image(
                painter = painterResource(id = R.drawable.ic_android),
                contentDescription = "Logo de Android",
                modifier = Modifier
                    .size(80.dp)
                    .clickable {
                        selectedPlatform = "Android"
                    },
                colorFilter = if (selectedPlatform == "Android") ColorFilter.tint(MaterialTheme.colorScheme.primary) else ColorFilter.tint(Color.Gray)
            )

            // Espaciado entre los logos
            Spacer(modifier = Modifier.width(64.dp))  // Espaciado entre los logos

            // Imagen de iOS
            Image(
                painter = painterResource(id = R.drawable.ic_ios),
                contentDescription = "Logo de iOS",
                modifier = Modifier
                    .size(80.dp)
                    .clickable {
                        selectedPlatform = "iOS"
                    },
                colorFilter = if (selectedPlatform == "iOS") ColorFilter.tint(MaterialTheme.colorScheme.primary) else ColorFilter.tint(Color.Gray)
            )
        }



        Spacer(modifier = Modifier.height(24.dp))

        Text("Seleccioná tus preferencias:")

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // Reordenamos para que "Otra" esté siempre al final
            val sortedPreferences = preferences.toSortedMap(compareBy {
                if (it == "Otra") "zzzz" else it
            })

            sortedPreferences.forEach { (label, isChecked) ->
                Card(
                    modifier = Modifier
                        .toggleable(
                            value = isChecked,
                            onValueChange = { preferences[label] = it }
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isChecked) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = label)
                    }
                }
            }
        }


        // Si elige "Otra", mostrar campo extra
        if (preferences["Otra"] == true) {
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = otherPreference,
                onValueChange = { otherPreference = it },
                label = { Text("Especificá tu preferencia") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("start") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar sesión")
        }
    }
}
