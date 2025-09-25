package com.example.semana01.screens.login

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable2D
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.semana01.R
import com.example.semana01.data.UsuarioRepositorio
import androidx.compose.ui.graphics.Color
import com.google.firebase.auth.FirebaseAuth

@Composable
fun loginScreen(navRouter: NavController) {
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var context = LocalContext.current
    var passwordVisible by remember { mutableStateOf(false) }
    UsuarioRepositorio.usuarioPrecargado()


    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding()
            .background(Color(0xFFFAFAFA))
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){

        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.recipesbookanimation)
        )

        val progress by animateLottieCompositionAsState(
            composition,
            iterations = LottieConstants.IterateForever

        )

        LottieAnimation(
            composition = composition,
            progress = {progress},
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text (
            text = "Minuta nutricional",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text (
            text = "Iniciar sesión para continuar",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            placeholder = {
                Text("Correo electrónico",
                    color = Color.Gray,
                    style = TextStyle (textAlign = TextAlign.Center),
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

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = {
                Text("Contraseña",
                    color = Color.Gray,
                    style = TextStyle (textAlign = TextAlign.Center),
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
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible) R.drawable.visible else R.drawable.ojo
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                        modifier = Modifier.size(16.dp),
                    )
                }}
        )

        Spacer(modifier = Modifier.height(24.dp))
        /*
        Button(
            onClick = {
                val usuarioEncontrado = UsuarioRepositorio.obtenerUsuarios().find {
                    it.nombreusuario == correo && it.password == password
                }
                if (usuarioEncontrado != null) {
                    navRouter.navigate("homeScreen")
                } else {
                    Toast.makeText(context, "correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFD9C00)),
            shape = RoundedCornerShape(4.dp)
        ){
            Text("Iniciar sesión", fontSize = 16.sp, color = Color.White)

        }
        */

        Button(
            onClick = {
                val auth = FirebaseAuth.getInstance()
                auth.signInWithEmailAndPassword(correo, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            navRouter.navigate("homeScreen")
                        } else {
                            Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                        }
                    }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFD9C00)),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text("Iniciar sesión", fontSize = 16.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Recuperar contraseña",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1E88E5),
            textAlign = TextAlign.Center,
            modifier = Modifier.clickable{
                navRouter.navigate("recoverScreen")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {

            Text(
                text = "¿No tienes una cuenta?",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Registrate",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF1E88E5),
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable {
                    navRouter.navigate("registerScreen")
                }
            )
        }



    }
}