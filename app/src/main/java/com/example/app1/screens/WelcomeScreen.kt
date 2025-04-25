package com.example.app1.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
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
import com.example.app1.components.TypewriterText
import com.example.app1.ui.theme.AccentPink
import com.example.app1.ui.theme.App1Theme
import com.example.app1.ui.theme.DarkText
import com.example.app1.ui.theme.JetBrainsMono
import com.example.app1.ui.theme.LocalAppGradients

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WelcomeScreen(navController: NavController) {
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
    var selectedPlatform by remember { mutableStateOf<String?>(null) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LocalAppGradients.current.background)
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

                    Spacer(modifier = Modifier.height(28.dp))

            TypewriterText(
                text = "¡Bienvenido a la app, Juan Torres!",
                delayMillis = 50L,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 80.dp)
                    .padding(start = 8.dp, end = 8.dp, bottom = 24.dp),
                textAlign = TextAlign.Start,
                textStyle = MaterialTheme.typography.headlineSmall.copy(
                    fontFamily = JetBrainsMono,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                ),
                color = DarkText,
            )









            Text(
                text = "Seleccioná tu plataforma:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = JetBrainsMono,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf("Android" to R.drawable.ic_android, "iOS" to R.drawable.ic_ios).forEach { (platform, iconRes) ->
                    val isSelected = selectedPlatform == platform

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(
                                if (isSelected) AccentPink.copy(alpha = 0.1f) else Color.Transparent
                            )
                            .border(
                                width = if (isSelected) 2.dp else 1.dp,
                                color = if (isSelected) AccentPink else Color.Gray.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(24.dp)
                            )
                            .clickable { selectedPlatform = platform }
                            .padding(vertical = 20.dp, horizontal = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = iconRes),
                            contentDescription = "Logo de $platform",
                            modifier = Modifier.size(96.dp),
                            colorFilter = ColorFilter.tint(
                                if (isSelected) AccentPink else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        )

                       
                    }
                }
            }




            Spacer(modifier = Modifier.height(34.dp))
            Text(
                text = "Seleccioná tus preferencias:",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontFamily = JetBrainsMono,
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                val sorted = preferences.toSortedMap(compareBy { if (it == "Otra") "zzzz" else it })
                sorted.forEach { (label, checked) ->
                    Card(
                        modifier = Modifier.toggleable(
                            value = checked,
                            onValueChange = { preferences[label] = it }
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = if (checked)
                                MaterialTheme.colorScheme.primaryContainer
                            else
                                MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = checked,
                                onCheckedChange = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = label,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontFamily = JetBrainsMono,
                                    fontSize = 14.sp
                                )
                            )
                        }
                    }
                }
            }


            val otraFieldHeight = 100.dp
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(otraFieldHeight)   // reservamos espacio fijo
            ) {
                if (preferences["Otra"] == true) {
                    OutlinedTextField(
                        value = otherPreference,
                        onValueChange = { otherPreference = it },

                        label = {
                            Text(
                                "Especificá tu preferencia",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontFamily = JetBrainsMono
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White.copy(alpha = 0.3f),
                            focusedContainerColor = Color.White.copy(alpha = 0.3f),
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            cursorColor = MaterialTheme.colorScheme.primary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
                Spacer(modifier = Modifier.height(150.dp))
                Button(
                    onClick = { navController.navigate("start") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AccentPink,
                        contentColor = Color.White
                    )
                ) {
                    Text("Comenzar", fontSize = 16.sp, fontWeight = FontWeight.Bold)


                }

        }
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