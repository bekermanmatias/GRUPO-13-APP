package com.example.app1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.app1.ui.theme.App1Theme
import com.example.app1.ui.navigation.NavHostScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Usamos App1Theme para envolver la aplicación con el tema personalizado
            App1Theme {
                // Aquí aplicamos el tema y luego iniciamos la pantalla principal
                NavHostScreen()  // Iniciamos el NavHost para la navegación
            }
        }
    }
}
