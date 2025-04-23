package com.example.app1.screens

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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app1.R
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    /*
    Box principal: fondo y blur.
    Box es una caja contenedora que permite superponer cosas.
    Fondo degradado - Imagen - Texto encima.

    Esto crea un fondo con degradado vertical (de violeta a azul)
    que ocupa toda la pantalla.
    */
    Box(
        modifier = Modifier
            /*
            Es lo que usamos para darle estilo y comportamient
            a un elemento. Es como decir "esto va a ocupar tanta cantidad",
            o "esto está centrado".
             */

            .fillMaxSize()
            //ocupa el espacio disponible

            .background(
                //fondo con degradado
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF6a11cb).copy(alpha = 0.9f),
                        Color(0xFF2575fc).copy(alpha = 0.9f)
                    )
                )
            )
            .systemBarsPadding()
            // es para que no se solape con la barra de estado del telefono
    )

    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(4.dp)
            //nuevamente una box en la cual le agregamos un desenfoque (efecto vidrio)
        )
        /*
        Columna principal, se encuentra tod0 el contendio centrado.
        En esto, se pone las cosas en el medio de la pantalla, bien ordenadas
        una debajo de la otra, con espacio alrededor.
         */
        Column(
            modifier = Modifier
                .fillMaxSize()
                //ocupa toda la pantalla  s
                .padding(24.dp),
                //agrega espacio interno.
            horizontalAlignment = Alignment.CenterHorizontally,
            //centra horizontalmente
            verticalArrangement = Arrangement.Center
            //centra verticalmente
        ) {
            /*
            Aca se agrega el logo y titulo, se enfoca en el logo de la app
            con bordes redondeads y un fondo claro translucido.
             */
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                //se trae la imagen logo
                contentDescription = "App Logo",
                //se agreaga una descripcion para accesibilidad
                modifier = Modifier
                    .size(140.dp)
                    //ajustamos el tamaño de la imagen.
                    //se podria estandarizar el tamaño de la imagen no?
                    .clip(RoundedCornerShape(24.dp))
                    //esquinas redondeadas
                    .background(White.copy(alpha = 0.1f)),
                    //fondo semitransparente
                contentScale = ContentScale.Crop
                //ajusta el contenido.
            )

            Spacer(modifier = Modifier.height(16.dp))


            //Texto grande, blanco, centrado, como titulo.
            Text(
                text = "Crear cuenta",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = White,
                    fontSize = 26.sp
                )
            )

            Spacer(modifier = Modifier.height(16.dp))
            /*
            Esta columna contiene los campos del formulario. Tiene un fondo
            blanco con transparencia (asi podemos ver el fondo), esquinas redondeadas,
            y espacio entre cada elemento.
             */
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White.copy(alpha = 0.15f), RoundedCornerShape(24.dp))
                    //tarjeta translucidad
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                /*
                Aca se encotraran todos los campos del formulario (texto)
                Por ejemplo en este codigo, es el campo para ingresar el nombre.
                 */

                /*
                Que es un OutlinedTextField?
                Es un campo de texto de Jetpack Comprose con borde.
                Viene con estilos modernos de Material Design:
                etiqueta flotante, linea inferior, cursor animado, etc.
                 */
                // Nombre
                OutlinedTextField(
                    value = name,
                    /*
                    es el estado actual del campo de texto

                    name es una variable tipo String, que guarda lo que el
                    usuario escribe.

                    Tiene que estar declarada arriba como
                    var name by remember { mutableStateOf("") }
                    Cada vez que el usuario escribe, el valor se actualiza.

                     */
                    onValueChange = { name = it },
                    /*
                    Esta funcion dice:
                    "Cuando el usuario escriba algo nuevo, actualiza name con ese nuevo texto"

                    it representa el nuevo texto.

                    Asi se mantiene sincronizado el valor del texto con lo que se muestra en pantalla
                     */

                    label = { Text("Nombre", color = White.copy(alpha = 0.8f)) },
                    /*
                    Esto crea la etiqueta flotante que aparece dentro del campo
                    (y se mueve arriba cuando el usuario escribe)
                    "Nombre" es el texto que muestra
                    color = ..., hace que sea blanco, pero ligeramente transparente.
                    es como un placeholder.
                     */
                    singleLine = true,
                    /*
                    le dice al campo:
                    No permitas que el usuario salte de linea. Tod0 en una sola linea.
                     */
                    modifier = Modifier.fillMaxWidth(),
                    /*
                    Aparece nuevamente el modificar visual, ocupa tod0 el ancho disponible del padre
                     */
                    colors = customTextFieldColors()
                    /*
                    Esta parte define los colores personalizados del campo.
                    Por defecto, los campos usan los colores del tema,
                    pero si estás trabajando sobre un fondo oscuro,
                    degradado, o transparente como nosotros
                    necesitás definir manualmente los colores.
                     */
                )

                // Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email", color = White.copy(alpha = 0.8f)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = customTextFieldColors()
                )

                // Contraseña
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña", color = White.copy(alpha = 0.8f)) },
                    singleLine = true,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        //Icono para mostrar u ocultar la contraseña.2
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = "Toggle Password",
                                tint = White
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = customTextFieldColors()
                )

                // Confirmar contraseña
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Repetir contraseña", color = White.copy(alpha = 0.8f)) },
                    singleLine = true,
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                            Icon(
                                imageVector = if (confirmPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = "Toggle Confirm Password",
                                tint = White
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = customTextFieldColors()
                )


                /*
                Acá se hace el boton para la registración.

                Este boton tambien tiene una validacion de datos:
                que no esten vacios los campos, que el email tenga forma valida y
                que las contraseñas coincidan.
                */
                Button(
                    onClick = {
                        val emailRegex = Regex("^[A-Za-z].*@[A-Za-z]+\\.[A-Za-z]+$")
                        when {
                            name.isBlank() || email.isBlank() -> coroutineScope.launch {
                                snackbarHostState.showSnackbar("Nombre y Email son obligatorios")
                            }
                            !email.matches(emailRegex) -> coroutineScope.launch {
                                snackbarHostState.showSnackbar("El email no es válido")
                            }
                            password.length < 6 -> coroutineScope.launch {
                                snackbarHostState.showSnackbar("La contraseña debe tener al menos 6 caracteres")
                            }
                            password != confirmPassword -> coroutineScope.launch {
                                snackbarHostState.showSnackbar("Las contraseñas no coinciden")
                            }
                            else -> navController.navigate("login")
                            //se hacen las validaciones, si está bien, te envia al login.
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = White,
                        //fondo del boton.
                        contentColor = Color(0xFF6a11cb)
                        //texto en violeta.
                    )
                ) {
                    Text("Registrarse", fontSize = 16.sp)
                }

                TextButton(onClick = { navController.navigate("login") }) {
                    Text("¿Ya tenés cuenta? Iniciar sesión", color = White)
                }
            }
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )

    }
}
/*
Esto te ahorra tener que repetir los mismos
colores personalizados cada vez que creás un OutlinedTextField.
 */
@Composable
fun customTextFieldColors() = TextFieldDefaults.colors(
    unfocusedContainerColor = Color.Transparent,
    focusedContainerColor = Color.Transparent,
    unfocusedTextColor = White,
    focusedTextColor = White,
    cursorColor = White,
    unfocusedIndicatorColor = White.copy(alpha = 0.5f),
    focusedIndicatorColor = White
)
/*
Parámetro	¿Qué hace?
unfocusedContainerColor	Fondo del campo cuando NO está seleccionado (transparente)
focusedContainerColor	Fondo cuando SÍ está seleccionado (también transparente)
unfocusedTextColor	Color del texto cuando no está enfocado (blanco)
focusedTextColor	Color del texto cuando está enfocado (blanco)
cursorColor	Color del cursor (la rayita que parpadea al escribir) (blanco)
unfocusedIndicatorColor	Línea inferior cuando no está enfocado (blanco, semi-transparente)
focusedIndicatorColor	Línea inferior cuando está enfocado (blanco, fuerte)
 */
