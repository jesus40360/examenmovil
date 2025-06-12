package com.example.examenmovil

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.banco.CuentaBanco

class MainActivity : AppCompatActivity() {

    private lateinit var txtUsuario: EditText
    private lateinit var txtContrasena: EditText
    private lateinit var btnIngresar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtUsuario = findViewById(R.id.txtUsuario)
        txtContrasena = findViewById(R.id.txtContrasena)
        btnIngresar = findViewById(R.id.btnIngresar)

        btnIngresar.setOnClickListener {
            val usuario = txtUsuario.text.toString()
            val contrasena = txtContrasena.text.toString()

            when {
                usuario.isBlank() -> {
                    Toast.makeText(this, getString(R.string.error_usuario), Toast.LENGTH_SHORT).show()
                }
                contrasena.isBlank() -> {
                    Toast.makeText(this, getString(R.string.error_contrasena), Toast.LENGTH_SHORT).show()
                }
                usuario == "admin" && contrasena == "1234" -> {
                    val intent = Intent(this, CuentaBanco::class.java)
                    intent.putExtra("usuario", usuario)
                    startActivity(intent)
                }
                else -> {
                    Toast.makeText(this, getString(R.string.error_credenciales), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
