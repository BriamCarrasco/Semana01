package com.example.semana01.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.semana01.R
import com.example.semana01.screens.topbar.BottomBar
import com.example.semana01.screens.topbar.topBarBack
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Card
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.semana01.data.RecetasRepositorio
import com.example.semana01.data.Receta
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.semana01.drawer.DrawerScreen


@Composable
fun homeScreen(navRouter: NavController, onBack: () -> Unit) {
    DrawerScreen(navRouter = navRouter) { openDrawer ->
        Scaffold(
            topBar = {
                topBarBack(
                    onBackClick = onBack,
                    title = ""
                )
            },
            bottomBar = {
                BottomBar(
                    navController = navRouter,
                    onSettingsClick = openDrawer
                )
            }
        ) { innerPadding ->

            RecetasRepositorio.recetasPrecargadas()
            val recetas = RecetasRepositorio.obtenerRecetas()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5))
                    .imePadding()
                    .padding(horizontal = 24.dp)
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val composition by rememberLottieComposition(
                    LottieCompositionSpec.RawRes(R.raw.recipesbookanimation)
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

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(recetas) { receta ->
                        RecetaCard(receta, navRouter)
                    }
                }
            }
        }
    }
}

@Composable
fun RecetaCard(receta: Receta, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("receta/${receta.idReceta}")
            },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = receta.imgURL),
                contentDescription = receta.nombreReceta,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Log.d("RecetaCard", "Cargando imagen con id: ${receta.imgURL}")

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = receta.nombreReceta,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color(0xFFFD9C00)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = receta.descCorta,
                    fontSize = 12.sp,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Porciones: ${receta.porciones}",
                    fontSize = 12.sp,
                    color = Color(0xFFFD9C00)
                )
                Row {
                    Text(
                        text = "Dificultad: ",
                        fontSize = 12.sp,
                        color = Color(0xFFFD9C00)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = receta.dificultad,
                        fontSize = 12.sp,
                        color = Color.LightGray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Tiempo: ",
                        fontSize = 12.sp,
                        color = Color(0xFFFD9C00)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = receta.tiempoPreparacion.toString(),
                        fontSize = 12.sp,
                        color = Color.LightGray
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    homeScreen(navRouter = rememberNavController(), onBack = {})
}

