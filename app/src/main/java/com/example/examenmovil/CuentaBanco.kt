package com.example.banco

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.examenmovil.R

class CuentaBanco : AppCompatActivity() {

    private lateinit var txtCuenta: EditText
    private lateinit var txtCliente: EditText
    private lateinit var txtBanco: EditText
    private lateinit var txtSaldo: EditText
    private lateinit var txtCantidad: EditText

    private lateinit var rdbDeposito: RadioButton
    private lateinit var rdbRetiro: RadioButton
    private lateinit var rdbConsulta: RadioButton

    private lateinit var btnRegistrar: Button
    private lateinit var btnAplicar: Button

    private var saldoActual = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuenta_banco)

        val usuario = intent.getStringExtra("usuario") ?: "Cliente"
        Toast.makeText(this, "Bienvenido, $usuario", Toast.LENGTH_SHORT).show()

        txtCuenta = findViewById(R.id.txtCuenta)
        txtCliente = findViewById(R.id.txtCliente)
        txtBanco = findViewById(R.id.txtBanco)
        txtSaldo = findViewById(R.id.txtSaldo)
        txtCantidad = findViewById(R.id.txtCantidad)

        rdbDeposito = findViewById(R.id.rdbDeposito)
        rdbRetiro = findViewById(R.id.rdbRetiro)
        rdbConsulta = findViewById(R.id.rdbConsulta)

        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnAplicar = findViewById(R.id.btnAplicar)

        btnRegistrar.setOnClickListener {
            val cuenta = txtCuenta.text.toString()
            val cliente = txtCliente.text.toString()
            val banco = txtBanco.text.toString()
            val saldo = txtSaldo.text.toString().toFloatOrNull() ?: -1f

            val cuentaObj = RegistrarCuenta(cuenta, cliente, banco, saldo)

            if (!cuentaObj.esValido()) {
                Toast.makeText(this, getString(R.string.error_datos_cuenta), Toast.LENGTH_SHORT).show()
            } else {
                saldoActual = cuentaObj.saldoInicial
                Toast.makeText(this, getString(R.string.registrar) + " exitoso", Toast.LENGTH_SHORT).show()
            }
        }

        btnAplicar.setOnClickListener {
            val cantidad = txtCantidad.text.toString().toFloatOrNull()

            if ((!rdbDeposito.isChecked && !rdbRetiro.isChecked && !rdbConsulta.isChecked) || cantidad == null) {
                Toast.makeText(this, getString(R.string.error_movimiento), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            when {
                rdbDeposito.isChecked -> {
                    saldoActual += cantidad
                    Toast.makeText(this, getString(R.string.deposito) + " realizado", Toast.LENGTH_SHORT).show()
                }
                rdbRetiro.isChecked -> {
                    if (cantidad > saldoActual) {
                        Toast.makeText(this, getString(R.string.error_sin_saldo), Toast.LENGTH_SHORT).show()
                    } else {
                        saldoActual -= cantidad
                        Toast.makeText(this, getString(R.string.retiro) + " realizado", Toast.LENGTH_SHORT).show()
                    }
                }
                rdbConsulta.isChecked -> {
                    Toast.makeText(this, "Saldo actual: $$saldoActual", Toast.LENGTH_SHORT).show()
                }
            }

            txtSaldo.setText(saldoActual.toString())
        }
    }
}
