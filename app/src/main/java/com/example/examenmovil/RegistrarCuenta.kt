package com.example.banco

data class RegistrarCuenta(
    var numeroCuenta: String,
    var nombreCliente: String,
    var banco: String,
    var saldoInicial: Float
) {
    fun esValido(): Boolean {
        return numeroCuenta.isNotBlank() &&
                nombreCliente.isNotBlank() &&
                banco.isNotBlank() &&
                saldoInicial >= 0
    }
}
