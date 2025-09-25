package com.example.semana01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.lifecycle.lifecycleScope
import com.example.semana01.ui.theme.Semana01Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.semana01.nav.NavRouter
import com.example.semana01.screens.login.loginScreen
import com.example.semana01.screens.home.homeScreen
import com.example.semana01.screens.recoverpass.recoverScreen
import com.example.semana01.screens.register.registerScreen
import com.example.semana01.screens.register.RegisterViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.semana01.screens.receta.recetaScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.*
import com.google.firebase.auth.FirebaseAuth


class MainActivity : ComponentActivity() {

    private var isLoading = true

    @OptIn(ExperimentalAnimationApi::class)
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
                val navController = rememberNavController()
                val user = FirebaseAuth.getInstance().currentUser
                val startDestination = if (user != null) {
                    NavRouter.HomeScreen.route
                } else {
                    NavRouter.LoginScreen.route
                }
                AnimatedNavHost(
                    navController = navController,
                    startDestination = startDestination,
                    enterTransition = { slideInHorizontally() },
                    exitTransition = { slideOutHorizontally() }
                ) {
                    composable(NavRouter.LoginScreen.route) {
                        loginScreen(navController)
                    }
                    composable(NavRouter.HomeScreen.route) {
                        homeScreen(navController, onBack = {
                            navController.popBackStack()
                        })
                    }
                    composable(NavRouter.RegisterScreen.route) {
                        val registerViewModel: RegisterViewModel = viewModel()
                        registerScreen(navController, registerViewModel)
                    }
                    composable(NavRouter.RecoverScreen.route) {
                        recoverScreen(navController, onBack = {
                            navController.popBackStack()
                        })
                    }
                    composable("receta/{idReceta}") { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("idReceta")
                        recetaScreen(id = id, navRouter = navController, onBack = {
                            navController.popBackStack()
                        })
                    }
                }
            }
        }
    }
}

/*

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
            .verticalScroll(rememberScrollState())
            .imePadding()
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
            value = userName,
            onValueChange = { userName = it },
            placeholder = {
                Text("Nombre de usuario",
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

        Button(
            onClick = {
                val usuarioEncontrado = UsuarioRepositorio.obtenerUsuarios().find {
                    it.nombreusuario == userName && it.password == password
                }
                if (usuarioEncontrado != null) {
                    context.startActivity(Intent(context, Home::class.java))
                } else {
                    Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFD9C00)),
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
*/

