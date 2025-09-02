package com.example.semana01.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.semana01.R
import com.example.semana01.screens.topbar.topBarBack


@Composable
fun RegisterStep1(viewModel: RegisterViewModel, onNext: () -> Unit, onBack: ()-> Unit) {
    val context = LocalContext.current
    val correo = viewModel.correo
    val isEmailValid = viewModel.isValidEmail(correo)

    Scaffold(
        topBar = {
            topBarBack(
                onBackClick = onBack,
                title = ""
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFFAFAFA))
                .imePadding()
                .padding(horizontal = 32.dp)
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.kitchen)
            )

            val progress by animateLottieCompositionAsState(
                composition,
                iterations = LottieConstants.IterateForever

            )

            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Registro",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Paso 1: Ingresa tu correo")

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = viewModel.correo,
                onValueChange = {
                    viewModel.correo = it
                },
                placeholder = {
                    Text(
                        "Correo electrónico",
                        color = Color.Gray,
                        style = TextStyle(textAlign = TextAlign.Center),
                        modifier = Modifier.fillMaxWidth()

                    )
                },
                modifier = Modifier.fillMaxWidth()
                    .height(54.dp)
                    .background(Color.White, shape = RoundedCornerShape(4.dp)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFD9C00),
                    unfocusedTextColor = Color(0xFFFD9C00),
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = Color.Black
                ),
                shape = RoundedCornerShape(4.dp),
                singleLine = true,
                isError = !isEmailValid && viewModel.correo.isNotBlank()
            )

            if (!isEmailValid && viewModel.correo.isNotBlank()) {
                Text(
                    text = "Correo inválido",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onNext() },
                enabled = isEmailValid,
                modifier = Modifier.padding(top = 16.dp).align(Alignment.End),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFFD9C00),
                    contentColor = Color.White
                )
            ) {
                Text("Siguiente")
            }
        }
    }
}

