package com.example.semana01.screens.register

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.semana01.MainActivity
import com.example.semana01.R
import com.example.semana01.screens.topbar.BottomBar
import com.example.semana01.screens.topbar.topBarBack

@Composable
fun RegisterStep2(viewModel: RegisterViewModel,onNext: () -> Unit, onBack: () -> Unit) {


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
                .imePadding()
                .background(Color(0xFFFAFAFA))
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

            Text("Paso 2: Cuenta")

            OutlinedTextField(
                value = viewModel.nombreUsuario,
                onValueChange = { viewModel.nombreUsuario = it },
                placeholder = {
                    Text(
                        "Nombre de usuario",
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
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

                Button(
                    onClick = { onBack() },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFFD9C00),
                        contentColor = Color.White
                    )
                ) {
                    Text("Atrás")
                }

                Button(
                    onClick = { onNext() },
                    modifier = Modifier.padding(top = 16.dp),
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
}
