package com.example.semana01.screens.receta

import android.R
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.semana01.data.RecetasRepositorio
import com.example.semana01.screens.topbar.topBarBack
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import kotlinx.coroutines.sync.Mutex
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.semana01.screens.topbar.BottomBar
import androidx.compose.runtime.setValue

@Composable
fun recetaScreen(navRouter: NavController, id: String?, onBack: ()-> Unit){

    RecetasRepositorio.recetasPrecargadas()

    val recetas = RecetasRepositorio.obtenerRecetas()

    val recetaencontrada = recetas.find { it.idReceta.toString() == id }

    var ShowDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            topBarBack(
                onBackClick = onBack,
                title = ""
            )
        },
        bottomBar = { BottomBar(navController = navRouter) }
    ){innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFAFAFA))
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            recetaencontrada?.let{
                Image(
                    painter = painterResource(id = it.imgURL),
                    contentDescription = it.nombreReceta,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = recetaencontrada?.nombreReceta ?: "Receta no encontrada",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                    fontSize = 25.sp,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold

            )

            Spacer(modifier = Modifier.height(24.dp))


            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_media_ff),
                    contentDescription = "Dificultad",
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Dificultad: ",
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = recetaencontrada?.dificultad ?: "",
                    fontSize = 14.sp,
                    color = Color.DarkGray

                    )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = recetaencontrada?.descCorta ?: "",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                fontSize = 14.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Justify

            )

            Spacer(modifier = Modifier.height(24.dp))

            Spacer(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.height(12.dp))

            val tiempo = "Tiempo: " + recetaencontrada?.tiempoPreparacion + " min"
            Text(
                text = tiempo,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                fontSize = 12.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,

            )

            Spacer(modifier = Modifier.height(12.dp))

            Spacer(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .background(Color.Gray)

            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Ingredientes",
                modifier = Modifier
                    .padding(horizontal = 25.dp)
                    .fillMaxWidth(),
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .padding(horizontal = 25.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Porciones",
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${recetaencontrada?.porciones} porciones",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )


            }

            Spacer(modifier = Modifier.height(24.dp))

            recetaencontrada?.ingredientes?.forEach { ingrediente ->
                val ingredienteText = "- " + ingrediente.cantidad + " " + ingrediente.unidad + " de " + ingrediente.nombreIngrediente
                Text(
                    text = ingredienteText,
                    modifier = Modifier
                        .padding(horizontal = 25.dp)
                        .fillMaxWidth(),
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Justify

                )

                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Instrucciones",
                modifier = Modifier
                    .padding(horizontal = 25.dp)
                    .fillMaxWidth(),
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center

            )

            Spacer(modifier = Modifier.height(24.dp))

            recetaencontrada?.instrucciones?.forEachIndexed { index, instruccion ->
                val instruccionText = "${index + 1}. $instruccion"
                Row(
                    modifier = Modifier
                        .padding(horizontal = 25.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .background(Color(0xFFFFC107), shape = RoundedCornerShape(50))
                            .align(Alignment.CenterVertically),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${index + 1}",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = instruccion,
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))


            }

            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { ShowDialog = true },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Ver información nutricional")
            }

            if (ShowDialog && recetaencontrada?.infoNutricional != null) {
                AlertDialog(
                    onDismissRequest = { ShowDialog = false },
                    title = { Text("Información Nutricional") },
                    text = {
                        val info = recetaencontrada.infoNutricional
                        Text(
                            "Calorías: ${info?.calorias ?: "-"} kcal\n" +
                                    "Proteína: ${info?.proteina ?: "-"} g\n" +
                                    "Grasas: ${info?.grasas ?: "-"} g\n" +
                                    "Carbohidratos: ${info?.carbohidratos ?: "-"} g"
                        )
                    },
                    confirmButton = {
                        Button(onClick = { ShowDialog = false }) {
                            Text("Cerrar")
                        }
                    }
                )
            }
    }
}


}