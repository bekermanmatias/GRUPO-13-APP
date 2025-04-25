package com.example.app1.screens

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll // <--- IMPORTANTE: Asegúrate de tener este import
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState // <--- IMPORTANTE: Asegúrate de tener este import
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app1.R
import com.example.app1.components.ErrorMessage
import com.example.app1.components.TypewriterText
import com.example.app1.ui.theme.*

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WelcomeScreen(navController: NavController) {
    var selectedPlatform by remember { mutableStateOf<String?>(null) }
    val opciones = listOf(
        "Hardware", "Programación", "Redes", "Seguridad", "DevOps", "Blockchain",
        "IA", "Bases de Datos", "APIs REST", "Frontend", "Backend", "Git",
        "Cloud", "Linux", "Windows Server", "Testing", "UI/UX", "Otra"
    )
    val preferenciasSeleccionadas = remember { mutableStateListOf<String>() }
    var otraPreferencia by remember { mutableStateOf(TextFieldValue("")) }
    var showDialog by remember { mutableStateOf(false) }
    var errorOtra by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LocalAppGradients.current.background)
            .systemBarsPadding()
    ) {
        // Fondo difuminado
        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(4.dp)
        )

        // Scroll vertical GENERAL para toda la pantalla si es necesario
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Scroll vertical para toda la columna
                .padding(16.dp),
            horizontalAlignment = Alignment.Start // Alineación a la izquierda
        ) {
            Spacer(modifier = Modifier.height(28.dp))

            // Tipeo de bienvenida
            TypewriterText(
                text = "¡Bienvenido a la app, Juan Torres!",
                delayMillis = 50L,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 80.dp)
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                textAlign = TextAlign.Start,
                textStyle = MaterialTheme.typography.headlineSmall.copy(
                    fontFamily = JetBrainsMono,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp, // Aumentar el tamaño de la fuente
                ),
                color = DarkText,
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Selección de plataforma
            Text(
                text = "Seleccioná tu plataforma:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = JetBrainsMono,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp // Mantener el mismo tamaño
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf("Android" to R.drawable.ic_android, "iOS" to R.drawable.ic_ios)
                    .forEach { (platform, iconRes) ->
                        val isSelected = selectedPlatform == platform
                        val scale by animateFloatAsState(if (isSelected) 1.1f else 1.0f)

                        Column(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .clip(RoundedCornerShape(24.dp))
                                .background(if (isSelected) AccentPink.copy(alpha = 0.1f) else Color.Transparent)
                                .border(
                                    width = if (isSelected) 2.dp else 1.dp,
                                    color = if (isSelected) AccentPink else Color.Gray.copy(alpha = 0.2f),
                                    shape = RoundedCornerShape(24.dp)
                                )
                                .clickable { selectedPlatform = platform }
                                .padding(vertical = 16.dp, horizontal = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = iconRes),
                                contentDescription = "Logo de $platform",
                                modifier = Modifier
                                    .size(96.dp)
                                    .scale(scale),
                                colorFilter = ColorFilter.tint(
                                    if (isSelected) AccentPink else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                )
                            )
                        }
                    }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Selección de preferencias
            Text(
                text = "Seleccioná tus preferencias:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = JetBrainsMono,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp // Mantener el mismo tamaño
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Contenedor ÚNICO: Aumentar altura para mostrar todas las preferencias
            Box(
                modifier = Modifier
                    .height(180.dp) // Aumentar la altura para acomodar más filas
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .horizontalScroll(rememberScrollState()) // Habilita scroll horizontal
            ) {
                // Agrupamos los chips en filas
                Column {
                    opciones.chunked(6).forEach { fila -> // Cambia 6 por el número de chips que deseas por fila
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp) // Espacio horizontal entre chips
                        ) {
                            fila.forEach { opcion ->
                                ChipPreferencia(
                                    opcion = opcion,
                                    seleccionada = preferenciasSeleccionadas.contains(opcion),
                                    onClic = {
                                        if (preferenciasSeleccionadas.contains(opcion)) {
                                            preferenciasSeleccionadas.remove(opcion)
                                            if (opcion == "Otra") otraPreferencia = TextFieldValue("")
                                        } else {
                                            preferenciasSeleccionadas.add(opcion)
                                        }
                                    }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp)) // Espacio vertical entre filas
                    }
                }
            }

            // Campo "Otra" animado
            AnimatedVisibility(
                visible = "Otra" in preferenciasSeleccionadas,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                OutlinedTextField(
                    value = otraPreferencia,
                    onValueChange = { otraPreferencia = it },
                    label = { Text("Especificá tu preferencia", color = DarkText.copy(alpha = 0.8f)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    textStyle = LocalTextStyle.current.copy(fontFamily = JetBrainsMono),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = AccentPink,
                        unfocusedBorderColor = Color.Gray.copy(alpha = 0.3f),
                        cursorColor = AccentPink,
                        focusedContainerColor = Color.White.copy(alpha = 0.3f),
                        unfocusedContainerColor = Color.White.copy(alpha = 0.3f)
                    )
                )



            }

            Spacer(modifier = Modifier.height(10.dp))

            errorMessage?.let {
                ErrorMessage(message = it, modifier = Modifier.padding(bottom = 12.dp))
            }


            // Botón final
            Button(
                onClick = {
                    when {
                        selectedPlatform == null -> {
                            errorMessage = "Por favor seleccioná una plataforma."
                        }
                        preferenciasSeleccionadas.isEmpty() -> {
                            errorMessage = "Seleccioná al menos una preferencia."
                        }
                        preferenciasSeleccionadas.contains("Otra") && otraPreferencia.text.length < 3 -> {
                            errorMessage = "'Otra' debe tener al menos 3 caracteres."
                            errorOtra = true
                        }
                        else -> {
                            errorMessage = null
                            errorOtra = false
                            showDialog = true
                        }
                    }
                }
                ,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AccentPink,
                    contentColor = Color.White
                ),
                enabled = selectedPlatform != null && preferenciasSeleccionadas.isNotEmpty() // Deshabilitar si no hay selección
            ) {
                Text("Comenzar", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = AccentPink,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Tu selección:",
                            color = DarkText,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                },
                text = {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.padding(horizontal = 4.dp)
                    ) {
                        Text(
                            "Plataforma:",
                            fontWeight = FontWeight.Medium,
                            color = DarkText
                        )
                        Text(
                            "  $selectedPlatform",
                            fontSize = 16.sp,
                            color = DarkText,
                            lineHeight = 20.sp
                        )

                        Text(
                            "Preferencias:",
                            fontWeight = FontWeight.Medium,
                            color = DarkText
                        )

                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            preferenciasSeleccionadas.filter { it != "Otra" }.forEach { preferencia ->
                                Text("  • $preferencia", color = DarkText)
                            }

                            if (preferenciasSeleccionadas.contains("Otra") && otraPreferencia.text.isNotBlank()) {
                                Text("  • Otra: ${otraPreferencia.text}", color = DarkText)
                            }

                        }
                    }
                },
                confirmButton = {
                    Column {
                        Button(
                            onClick = {
                                navController.navigate("start")
                                showDialog = false
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 1.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = AccentPink,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Avanzar", fontSize = 16.sp)
                        }

                        Spacer(modifier = Modifier.height(0.1.dp))

                        OutlinedButton(
                            onClick = { showDialog = false },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 1.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = DarkText
                            )
                        ) {
                            Text("Modificar", fontSize = 16.sp)
                        }
                    }
                },
                containerColor = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
        }




    }
}

@Composable
fun ChipPreferencia(
    opcion: String,
    seleccionada: Boolean,
    onClic: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                if (seleccionada) AccentPink.copy(alpha = 0.2f)
                else MaterialTheme.colorScheme.surface
            )
            .border(
                width = 1.dp,
                color = if (seleccionada) AccentPink
                else Color.Gray.copy(alpha = 0.3f),
                shape = RoundedCornerShape(12.dp),
            )
            .clickable { onClic() }
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Text(
            text = opcion,
            color = if (seleccionada) AccentPink
            else MaterialTheme.colorScheme.onSurface,
            fontSize = 16.sp,
            fontFamily = JetBrainsMono,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWelcomeScreenLight() {
    App1Theme(darkTheme = false) {
        WelcomeScreen(navController = rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewWelcomeScreenDark() {
    App1Theme(darkTheme = true) {
        WelcomeScreen(navController = rememberNavController())
    }
}