package com.example.app1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etUserName: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private lateinit var tvErrorMsg: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Asigna el layout XML de login
        setContentView(R.layout.activity_login)

        etUserName = findViewById(R.id.etUserName)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegister)
        tvErrorMsg = findViewById(R.id.tvErrorMsg)

        btnLogin.setOnClickListener {
            val userName = etUserName.text.toString().trim()
            val password = etPassword.text.toString()

            // Validación simple de credenciales
            if (userName == "Juan Torres" && password == "1234utn") {
                tvErrorMsg.visibility = View.GONE
                // Si el login es exitoso, inicia WelcomeActivity pasando el nombre de usuario
                val intent = Intent(this, WelcomeActivity::class.java)
                intent.putExtra("userName", userName)
                startActivity(intent)
            } else {
                tvErrorMsg.text = "Datos de usuario o contraseña incorrectos"
                tvErrorMsg.visibility = View.VISIBLE
            }
        }

        btnRegister.setOnClickListener {
            // Navega a RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
