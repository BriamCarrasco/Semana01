package com.example.semana01.screens.register

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.semana01.data.UsuarioRepositorio
import com.example.semana01.data.Usuarios
import com.google.firebase.auth.FirebaseAuth

class RegisterViewModel : ViewModel() {
    var nombreUsuario by mutableStateOf("")
    var correo by mutableStateOf("")
    var password by mutableStateOf("")

    fun registrarUsuario() {
        val nuevoUsuario = Usuarios(
            nombreusuario = nombreUsuario,
            correo = correo,
            password = password
        )
        UsuarioRepositorio.agregarUsuario(nuevoUsuario)
    }

    fun isStep1Valid(): Boolean {
        return correo.isNotBlank()
    }

    fun isStep2Valid(): Boolean {
        return nombreUsuario.isNotBlank()
    }

    fun isStep3Valid(): Boolean {
        return password.isNotBlank()
        return correo.isNotBlank() && isValidEmail(correo)
    }

    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    fun registerUserFireBase(onResult: (Boolean, String?) -> Unit){
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(correo, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                } else {
                    onResult(false, task.exception?.message)
                }
            }
    }
}