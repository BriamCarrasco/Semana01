package com.example.semana01.data

data class Usuarios (
    val nombreusuario: String,
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

    fun usuarioPrecargado() {
        if (usuariosList.isEmpty()) {
            val usuarioTest = Usuarios(
                nombreusuario = "q",
                correo = "q@q.q",
                password = "q"
            )

            usuariosList.add(usuarioTest)
        }

    }
}