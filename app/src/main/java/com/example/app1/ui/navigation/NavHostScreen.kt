package com.example.app1.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.app1.screens.WelcomeScreen
import com.example.app1.screens.LoginScreen
import com.example.app1.screens.RegisterScreen
import  com.example.app1.screens.ContactScreen


@Composable
fun NavHostScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("contact") { ContactScreen(navController) }

    }
}
