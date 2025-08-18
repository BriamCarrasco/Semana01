package com.example.semana01

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    var nombreUsuario by mutableStateOf("")
    var nombre by mutableStateOf("")
    var apellidoP by mutableStateOf("")
    var apellidoM by mutableStateOf("")
    var correo by mutableStateOf("")
    var password by mutableStateOf("")

    fun registrarUsuario() {
        val nuevoUsuario = Usuarios(
            nombreusuario = nombreUsuario,
            nombre = nombre,
            apellidoP = apellidoP,
            ApellidoM = apellidoM,
            correo = correo,
            password = password
        )
        UsuarioRepositorio.agregarUsuario(nuevoUsuario)
    }
}
