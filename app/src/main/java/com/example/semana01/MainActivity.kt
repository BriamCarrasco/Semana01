package com.example.semana01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.example.semana01.ui.theme.Semana01Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import android.content.Intent
import android.widget.Space
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.*
import androidx.compose.ui.text.input.VisualTransformation

class MainActivity : ComponentActivity() {

    private var isLoading = true
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        splashScreen.setKeepOnScreenCondition { isLoading }
        lifecycleScope.launch {
            delay(1500)
            isLoading = false
        }

        setContent {
            Semana01Theme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color =  Color(0xFFF5F5F5)
                ){
                    LoginScreen()
                }

            }
        }
    }
}

@Preview
@Composable
fun LoginScreen(){
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var context = LocalContext.current
    var passwordVisible by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

    ){
        Image(
            painter = painterResource(id= R.drawable.logo2),
            contentDescription = "Logo",
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.Fit

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
            value = userName,
            onValueChange = { userName = it },
            label = { Text("Nombre de usuario") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
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

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFB4E330)),
            shape = RoundedCornerShape(4.dp)
        ){
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
                context.startActivity(Intent(context, passwordRecovery::class.java))
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
                    context.startActivity(Intent(context, signUp::class.java))
                }
            )
        }



    }
}


