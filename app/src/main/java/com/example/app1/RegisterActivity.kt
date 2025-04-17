package com.example.app1

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etRepeatPassword: EditText
    private lateinit var btnRegisterSubmit: Button
    private lateinit var tvErrorMsgReg: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Asigna el layout XML de registro
        setContentView(R.layout.activity_register)

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etRepeatPassword = findViewById(R.id.etRepeatPassword)
        btnRegisterSubmit = findViewById(R.id.btnRegisterSubmit)
        tvErrorMsgReg = findViewById(R.id.tvErrorMsgReg)

        btnRegisterSubmit.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()
            val repeatPassword = etRepeatPassword.text.toString()

            when {
                name.isEmpty() || email.isEmpty() -> {
                    tvErrorMsgReg.text = "Los campos Nombre y E-mail no deben estar vacíos."
                    tvErrorMsgReg.visibility = View.VISIBLE
                }
                password.length < 6 -> {
                    tvErrorMsgReg.text = "La contraseña debe tener al menos 6 caracteres."
                    tvErrorMsgReg.visibility = View.VISIBLE
                }
                password != repeatPassword -> {
                    tvErrorMsgReg.text = "Las contraseñas no coinciden."
                    tvErrorMsgReg.visibility = View.VISIBLE
                }
                else -> {
                    tvErrorMsgReg.visibility = View.GONE
                    // Aquí se podría realizar el registro real del usuario.
                    // Por ahora, finalizamos la actividad y volvemos al login.
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
