package com.example.semana01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.semana01.ui.theme.Semana01Theme
import androidx.compose.material.*

class resetPassword : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Semana01Theme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color =  Color(0xFFF5F5F5)
                ){
                    resetScreen()
                }
            }
        }
    }
}


@Preview
@Composable
fun resetScreen() {
    var currentpassword by remember { mutableStateOf("") }
    var newpassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }


    Column (
        modifier = Modifier.
        fillMaxSize()
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
            text = "Cambiar contraseña",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = currentpassword,
            onValueChange = { currentpassword = it },
            label = { Text("Contraseña actual") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = newpassword,
            onValueChange = { newpassword = it },
            label = { Text("Nueva contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFB4E330)),
            shape = RoundedCornerShape(4.dp)
        ){
            Text("Actualizar contraseña", fontSize = 16.sp, color = Color.White)

        }

        Spacer(modifier = Modifier.height(16.dp))



    }

}
