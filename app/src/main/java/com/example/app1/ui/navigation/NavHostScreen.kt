package com.example.app1.ui.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.app1.screens.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostScreen() {
    val navController = rememberNavController()

    // Usamos NavHost para gestionar las pantallas
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        // Pantalla Welcome con animación
        composable("welcome") {
            AnimatedVisibility(
                visible = true,
                enter = slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(700)),
                exit = slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(700))
            ) {
                WelcomeScreen(navController)
            }
        }

        // Pantalla Login con animación
        composable("login") {
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically(initialOffsetY = { 1000 }) + fadeIn(animationSpec = tween(700)),
                exit = slideOutVertically(targetOffsetY = { -1000 }) + fadeOut(animationSpec = tween(700))
            ) {
                LoginScreen(navController)
            }
        }

        // Pantalla Register con animación
        composable("register") {
            AnimatedVisibility(
                visible = true,
                enter = slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(700)),
                exit = slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(700))
            ) {
                RegisterScreen(navController)
            }
        }

        // Pantalla Contact con animación
        composable("contact") {
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(700)),
                exit = fadeOut(animationSpec = tween(700))
            ) {
                ContactScreen(navController)
            }
        }
    }
}
