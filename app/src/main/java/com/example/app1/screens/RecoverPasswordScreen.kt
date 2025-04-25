package com.example.app1.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.app1.R.drawable.logdef
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app1.components.ErrorMessage
import com.example.app1.ui.theme.AccentPink
import com.example.app1.ui.theme.DarkText
import com.example.app1.ui.theme.LocalAppGradients
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.app1.components.TypewriterText
import com.example.app1.ui.theme.App1Theme

@Composable
fun RecoverPasswordScreen(navController: NavController) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var message by remember { mutableStateOf("") }

    val gradientBackground = LocalAppGradients.current.background

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            // Logo + Texto animado
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 11.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = logdef), // Usar el mismo recurso de logo
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(140.dp)
                        .offset(x = (-16).dp)
                        .clip(RoundedCornerShape(24.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                TypewriterText(
                    text = "Ingresa tu Email para ayudarte!",
                    modifier = Modifier.padding(bottom = 1.dp, top = 1.dp),
                    color = DarkText,
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.3f), RoundedCornerShape(24.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ErrorMessage(message = message)

                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    label = { Text("Email asociado a tu cuenta", color = DarkText.copy(alpha = 0.8f)) },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
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

                Button(
                    onClick = {
                        message = if (email.text.isBlank()) {
                            "Ingresá un email para recuperar la contraseña"
                        } else {
                            "Te enviamos un email para restablecer tu contraseña"
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
                    Text("Enviar enlace", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }

                TextButton(onClick = { navController.popBackStack() }) {
                    Text("Volver al inicio de sesión", color = DarkText.copy(alpha = 0.9f))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewRecoverPasswordScreenLight() {
    App1Theme(darkTheme = false) {
        RecoverPasswordScreen(navController = rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewRecoverPasswordScreenDark() {
    App1Theme(darkTheme = true) {
        RecoverPasswordScreen(navController = rememberNavController())
    }
}
