package com.example.composeessentials

import android.os.Bundle
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeessentials.ui.theme.ComposeEssentialsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeEssentialsTheme {

                var num = 5

                Column {
                    showNum(num)
                    Abc(4) {newNum -> num = newNum}
                }


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

