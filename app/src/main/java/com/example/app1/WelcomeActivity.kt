package com.example.app1

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var tvWelcomeTitle: TextView
    private lateinit var rgPlataformas: RadioGroup
    private lateinit var ivLogo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Asigna el layout XML de welcome
        setContentView(R.layout.activity_welcome)

        tvWelcomeTitle = findViewById(R.id.tvWelcomeTitle)
        rgPlataformas = findViewById(R.id.rgPlataformas)
        ivLogo = findViewById(R.id.ivLogo)

        // Recibe el nombre del usuario desde el Intent
        val userName = intent.getStringExtra("userName") ?: "Usuario"
        tvWelcomeTitle.text = "Bienvenido a la aplicación $userName"

        // Escucha cambios en el grupo de radio para actualizar el logo
        rgPlataformas.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbAndroid -> {
                    ivLogo.setImageResource(R.drawable.ic_android)
                    ivLogo.visibility = ImageView.VISIBLE
                }
                R.id.rbIOS -> {
                    ivLogo.setImageResource(R.drawable.ic_ios)
                    ivLogo.visibility = ImageView.VISIBLE
                }
                else -> ivLogo.visibility = ImageView.GONE
            }
        }
    }
}
