package com.example.app1.ui.navigation

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import androidx.compose.animation.core.tween
import com.example.app1.screens.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("welcome") {
            // Aplicar animación en el composable de Welcome
            AnimatedVisibility(
                visible = true,
                enter = slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(500)),
                exit = slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(500))
            ) {
                WelcomeScreen(navController)
            }
        }

        composable("login") {
            // Aplicar animación en el composable de Login
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically(initialOffsetY = { 1000 }) + fadeIn(animationSpec = tween(500)),
                exit = slideOutVertically(targetOffsetY = { -1000 }) + fadeOut(animationSpec = tween(500))
            ) {
                LoginScreen(navController)
            }
        }

        composable("register") {
            // Aplicar animación en el composable de Register
            AnimatedVisibility(
                visible = true,
                enter = slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(500)),
                exit = slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(500))
            ) {
                RegisterScreen(navController)
            }
        }

        composable("contact") {
            // Aplicar animación en el composable de Contact
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(500)),
                exit = fadeOut(animationSpec = tween(500))
            ) {
                ContactScreen(navController)
            }
        }
    }
}
