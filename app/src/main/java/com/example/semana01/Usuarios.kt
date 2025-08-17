package com.example.semana01

import androidx.compose.runtime.mutableStateOf

data class Usuarios (
    val nombreusuario: String,
    val nombre: String,
    val apellidoP: String,
    val ApellidoM: String,
    val correo: String,
    val password: String
)

object UsuarioRepositorio{
    val usuariosList = mutableListOf<Usuarios>()


    fun agregarUsuario(usuario: Usuarios){
        usuariosList.add(usuario)
    }

    fun obtenerUsuarios(): List<Usuarios>{
        return usuariosList.toList()
    }
}