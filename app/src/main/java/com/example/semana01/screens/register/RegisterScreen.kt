package com.example.semana01.screens.register

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController


@Composable
fun registerScreen(navRouter: NavHostController, viewModel: RegisterViewModel) {
        var step by remember { mutableStateOf(1) }
        val context = LocalContext.current

    when (step) {
        1 -> RegisterStep1(
            viewModel = viewModel,
            onNext = {
                if (viewModel.isStep1Valid()) {
                    step = 2
                } else {
                    Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                }
            },
            onBack =  {
                navRouter.popBackStack()
            }
        )
        2 -> RegisterStep2(
            viewModel = viewModel,
            onNext = {
                if (viewModel.isStep2Valid()) {
                    step = 3
                } else {
                    Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                }
            },
            onBack = {
                step = 1
            }
        )
            3 -> RegisterStep3(
                viewModel = viewModel,
                onBack = {
                    step = 2
                },
                onRegister = {
                    if (viewModel.isStep3Valid()) {
                        viewModel.registrarUsuario()
                        Toast.makeText(context, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                        navRouter.popBackStack()
                    } else {
                        Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                    }
                }

            )
    }
}
