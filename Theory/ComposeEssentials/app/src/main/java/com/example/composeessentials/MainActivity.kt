package com.example.composeessentials

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeessentials.ui.theme.ComposeEssentialsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeEssentialsTheme {

//                var num = 5
//
//                Column {
//                    showNum(num)
//                    Abc(4) {newNum -> num = newNum}
//                }

                RecompositionTest()

                    // мислення в compose:
                        // "що", а не "як"
                        // ui - це функції
                        // стан контролює ui
                        // подія контролює стан


                    // ui описується за допомогою composable функцій
                    // Composable функція - функція
                        // для перетворення інформації,
                        // яка паредається в неї,
                        // в ui

                    // composable - незмінні
                        // не можна зебрігати посилання (val a = Text())
                        // не можна змінювати вміст
                        // оскільки це НЕ об'єкт


                    // Кожен раз виконує чисто свою роль, а саме -
                    // відображає дані у повному елементі ui
                        // однакова поведінка при повторних викликах
                        // не змінює властивості чи глобальні змінні
                    // це ідемпотентність

                    // наприклад:
                        // замість listSize = 6
                        // listSize = list.count()


                    // інтерфейс завжди узгоджений зі станом
                    // (якщо стан відслідковується через remember)
                    // при зміні стану інтерфейс перемалюється
                    // це і є "стан контролює ui"
                    // це називається перекомпозицією

            }
        }
    }
}

@Composable
fun showNum(num: Int) {
    Text("$num")
}

@Composable
fun Abc(num: Int, changeNum: (Int) -> Unit) {
    Text(
        modifier = Modifier.clickable {
            changeNum(num)
        },
        text = "Add item"
    )
}


// перекомпозиція відбувається у composable, які використовують стан, що змінився
// перекомпозиція починається з composable, в якому оголошено стан

@Composable
fun RecompositionTest() {
    Log.d("Compose test", "RecompositionTest was recomposed") // перекомпозовується, оскільки є взаємодія на рядку 112
    var isBlack: Boolean by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier.padding(30.dp)
    ) {
        MiddleLayer(isBlack) {isBlack = !isBlack}
    }
}

@Composable
fun MiddleLayer(isBlack: Boolean, changeColor: () -> Unit) {
    Log.d("Compose test", "MiddleLayer was recomposed") // перекопозовується, оскільки параметр отримує нове значення
    Column {
        Text(
            "Change color",
            modifier = Modifier.clickable { changeColor() }
        )
        ColorText(isBlack)
    }
}

@Composable
fun ColorText(isBlack: Boolean) {
    Log.d("Compose test", "ColorText was recomposed") // перекопозовується, оскільки параметр отримує нове значення
    Column {
        Text(
            "Text for testing...",
            color = if (isBlack) Color.Black else Color.Red
        )
        ChildText(isBlack)
    }
}

@Composable
fun ChildText(isBlack: Boolean) {
    Log.d("Compose test", "ChildText was recomposed") // перекомпозовується, оскільки параметр отримує нове значення
    Column {
        Text(
            "Another text"
        )
        LastLayer(isBlack)
    }
}

@Composable
fun LastLayer(isBlack: Boolean) {
    Log.d("Compose test", "LastLayer was recomposed") // не перекомпозовується, оскільки стан не використовується
    Text(
        "Last text",
        //color = if (isBlack) Color.Black else Color.Green
    )
}

