package com.example.semana01.data

import java.util.Date
import com.example.semana01.R

data class Receta (
    var idReceta: String,
    var nombreReceta: String,
    var descCorta: String,
    var imgURL: Int,
    var tiempoPreparacion: Int,
    var tiempoCoccion: Int,
    var porciones: Int,
    var ingredientes: List<Ingredientes>,
    var instrucciones: List<String>,
    var infoNutricional: InfoNutricional?,
    var categoria: String,
    var dificultad: String,
    var etiquetas: List<Etiquetas>,
    var fechaCreate: Date,
    var fechaUpdate: Date
    )

data class Ingredientes(
    val nombreIngrediente: String,
    val cantidad: Double,
    val unidad: String
)

data class InfoNutricional(
    val calorias: Int?, // Calorías en kcal
    val proteina: Double?, // Proteínas en gramos
    val grasas: Double?, // Grasas en gramos
    val carbohidratos: Double? // Carbohidratos en gramos
)

data class Etiquetas(
    var idReceta: String,
    var nombreEtiqueta: String
)


object RecetasRepositorio{
    val recetasList = mutableListOf<Receta>()

    fun obtenerRecetas(): List<Receta>{
        return recetasList.toList()
    }

    fun agregarReceta(receta: Receta){
        recetasList.add(receta)
    }

    fun recetasPrecargadas(){
        if (recetasList.isEmpty()) {
            val receta1 = Receta(
                idReceta = "1",
                nombreReceta = "Espagueti a la Carbonara",
                descCorta = "Deliciosa pasta italiana con salsa cremosa.",
                imgURL = R.drawable.carbonara,
                tiempoPreparacion = 15,
                tiempoCoccion = 20,
                porciones = 4,
                ingredientes = listOf(
                    Ingredientes("Espagueti", 400.0, "gramos"),
                    Ingredientes("Huevos", 4.0, "unidades"),
                    Ingredientes("Tocino", 150.0, "gramos"),
                    Ingredientes("Queso parmesano", 100.0, "gramos"),
                    Ingredientes("Pimienta negra", 1.0, "cucharadita")
                ),
                instrucciones = listOf(
                    "Cocer el espagueti según las instrucciones del paquete.",
                    "En un bol, batir los huevos y el queso parmesano rallado.",
                    "En una sartén, cocinar el tocino hasta que esté crujiente.",
                    "Combinar el espagueti con el tocino y retirar del fuego.",
                    "Mezclar rápidamente la mezcla de huevo y queso.",
                    "Sazonar con pimienta negra y servir de inmediato."
                ),
                infoNutricional = InfoNutricional(
                    calorias = 500,
                    proteina = 25.0,
                    grasas = 20.0,
                    carbohidratos = 60.0
                ),
                categoria = "Pasta",
                dificultad = "Medio",
                etiquetas = listOf(
                    Etiquetas("1", "Italiana"),
                    Etiquetas("1", "Cena")
                ),
                fechaCreate = java.util.Date(),
                fechaUpdate = java.util.Date()
            )

            val receta2 = Receta(
                idReceta = "2",
                nombreReceta = "Guacamole Casero",
                descCorta = "Una salsa tradicional mexicana, fresca y deliciosa, ideal para acompañar tus platos.",
                imgURL = R.drawable.guacamole,
                tiempoPreparacion = 10,
                tiempoCoccion = 0,
                porciones = 2,
                ingredientes = listOf(
                    Ingredientes("aguacates maduros", 2.0, "unidades"),
                    Ingredientes("cebolla roja", 0.5, "unidad"),
                    Ingredientes("cilantro fresco", 2.0, "cucharadas"),
                    Ingredientes("jugo de limón", 1.0, "cucharada"),
                    Ingredientes("sal", 0.5, "cucharadita")
                ),
                instrucciones = listOf(
                    "Machacar los aguacates en un bol hasta obtener la consistencia deseada.",
                    "Picar finamente la cebolla roja y el cilantro.",
                    "Mezclar todos los ingredientes y sazonar con sal y limón al gusto."
                ),
                infoNutricional = InfoNutricional(
                    calorias = 250,
                    proteina = 4.0,
                    grasas = 20.0,
                    carbohidratos = 15.0
                ),
                categoria = "Entrada",
                dificultad = "Muy fácil",
                etiquetas = listOf(
                    Etiquetas("2", "Mexicana"),
                    Etiquetas("2", "Vegetariana"),
                    Etiquetas("2", "Rápido")
                ),
                fechaCreate = java.util.Date(),
                fechaUpdate = java.util.Date()
            )

            val receta3 = Receta(
                idReceta = "3",
                nombreReceta = "Pollo Cremoso al Curry",
                descCorta = "Un plato lleno de sabor con especias de la India, cremoso y fácil de preparar.",
                imgURL = R.drawable.pollocurry,
                tiempoPreparacion = 10,
                tiempoCoccion = 30,
                porciones = 3,
                ingredientes = listOf(
                    Ingredientes("pechuga de pollo", 500.0, "gramos"),
                    Ingredientes("leche de coco", 1.0, "lata"),
                    Ingredientes("pasta de curry rojo", 2.0, "cucharadas"),
                    Ingredientes("cebolla", 1.0, "unidad"),
                    Ingredientes("jengibre rallado", 1.0, "cucharadita")
                ),
                instrucciones = listOf(
                    "Cortar el pollo en cubos y dorar en una sartén. Retirar y reservar.",
                    "En la misma sartén, saltear la cebolla y el jengibre hasta que estén blandos.",
                    "Incorporar la pasta de curry y cocinar por un minuto.",
                    "Verter la leche de coco y el pollo reservado. Dejar cocer a fuego lento por 15-20 minutos.",
                    "Servir con arroz basmati o pan naan."
                ),
                infoNutricional = InfoNutricional(
                    calorias = 450,
                    proteina = 35.0,
                    grasas = 25.0,
                    carbohidratos = 20.0
                ),
                categoria = "Principal",
                dificultad = "Intermedio",
                etiquetas = listOf(
                    Etiquetas("3", "India"),
                    Etiquetas("3", "Pollo")
                ),
                fechaCreate = java.util.Date(),
                fechaUpdate = java.util.Date()
            )
            val receta4 = Receta(
                idReceta = "4",
                nombreReceta = "Sopa de Lentejas",
                descCorta = "Un plato reconfortante y nutritivo, perfecto para los días fríos.",
                imgURL = R.drawable.sopadelentejas,
                tiempoPreparacion = 15,
                tiempoCoccion = 45,
                porciones = 4,
                ingredientes = listOf(
                    Ingredientes("Lentejas", 250.0, "gramos"),
                    Ingredientes("Zanahorias", 2.0, "unidades"),
                    Ingredientes("Cebolla", 1.0, "unidad"),
                    Ingredientes("Ajo", 2.0, "dientes"),
                    Ingredientes("Caldo de verduras", 1.5, "litros"),
                    Ingredientes("Tomate triturado", 200.0, "gramos"),
                    Ingredientes("Pimentón dulce", 1.0, "cucharadita")
                ),
                instrucciones = listOf(
                    "Lavar las lentejas y escurrirlas.",
                    "En una olla, sofreír la cebolla, el ajo y las zanahorias picadas.",
                    "Añadir las lentejas, el pimentón y el tomate triturado.",
                    "Verter el caldo de verduras y llevar a ebullición.",
                    "Reducir el fuego y cocinar a fuego lento durante 40-45 minutos o hasta que las lentejas estén tiernas."
                ),
                infoNutricional = InfoNutricional(
                    calorias = 350,
                    proteina = 20.0,
                    grasas = 5.0,
                    carbohidratos = 50.0
                ),
                categoria = "Sopa",
                dificultad = "Fácil",
                etiquetas = listOf(
                    Etiquetas("4", "Vegetariana"),
                    Etiquetas("4", "Invierno"),
                    Etiquetas("4", "Saludable")
                ),
                fechaCreate = java.util.Date(),
                fechaUpdate = java.util.Date()
            )

            val receta5 = Receta(
                idReceta = "5",
                nombreReceta = "Brownies de Chocolate",
                descCorta = "Un postre clásico, irresistiblemente denso y chocolatoso, con un toque de nueces.",
                imgURL = R.drawable.brownie,
                tiempoPreparacion = 10,
                tiempoCoccion = 25,
                porciones = 8,
                ingredientes = listOf(
                    Ingredientes("Mantequilla sin sal", 150.0, "gramos"),
                    Ingredientes("Chocolate negro", 200.0, "gramos"),
                    Ingredientes("Azúcar", 200.0, "gramos"),
                    Ingredientes("Huevos", 3.0, "unidades"),
                    Ingredientes("Harina de trigo", 100.0, "gramos"),
                    Ingredientes("Nueces picadas", 50.0, "gramos")
                ),
                instrucciones = listOf(
                    "Precalentar el horno a 180°C (350°F).",
                    "Derretir el chocolate y la mantequilla en un baño maría.",
                    "Retirar del fuego y mezclar con el azúcar.",
                    "Añadir los huevos uno a uno, batiendo bien después de cada adición.",
                    "Incorporar la harina y las nueces con movimientos envolventes.",
                    "Verter la mezcla en un molde y hornear por 20-25 minutos."
                ),
                infoNutricional = InfoNutricional(
                    calorias = 650,
                    proteina = 8.0,
                    grasas = 35.0,
                    carbohidratos = 80.0
                ),
                categoria = "Postre",
                dificultad = "Medio",
                etiquetas = listOf(
                    Etiquetas("5", "Postre"),
                    Etiquetas("5", "Chocolate"),
                    Etiquetas("5", "Dulce")
                ),
                fechaCreate = java.util.Date(),
                fechaUpdate = java.util.Date()
            )

            agregarReceta(receta1)
            agregarReceta(receta2)
            agregarReceta(receta3)
            agregarReceta(receta4)
            agregarReceta(receta5)
        }
    }

}


