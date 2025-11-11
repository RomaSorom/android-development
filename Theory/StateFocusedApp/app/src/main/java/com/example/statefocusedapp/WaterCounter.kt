package com.example.statefocusedapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WaterCounter(
    modifier: Modifier = Modifier,
    glasses: Int,
    onGlassesChanges: (Int) -> Unit
) {
    //var glasses: Int by rememberSaveable { mutableStateOf(value = 0) }
    // var showTask: Boolean by remember { mutableStateOf(value = true) } цей стан завжди зберігається в композиції,
    // оскільки remember завжди викликається
    Column(
        modifier = modifier
    ) {
        if (glasses > 0) { // якщо remember не виконуться під час композиції, композ забуває стан,
            // який цей remember зберігав в композиції

//            var showTask: Boolean by remember { mutableStateOf(value = true) }
//            if (showTask) {
//                TaskItem(
//                    text = "Some text",
//                ) { showTask = false }
//            }


            Text(
                text = "Glasses consumed: $glasses",
            )
        }
        Row { Button(onClick = { onGlassesChanges(1) },
                     enabled = glasses < 10) { Text(text = "+1 glass") } }


//            Button(
//                onClick = {
//                    glasses = 0
//                }
//            ) {
//                Text("Clear count")
//            }
    }
}


// composable бувають:
// statefull - composable має внутрішній стан, який зберігається через remember
    // викликач не взаємодіє зі станом цього composable

// stateless - composable не містить у собі стану, а отримує його від викликача
// для утворення stateless використовується підняття стану (state hoisting)

// state hoisting - це передче внутрішнього стану композбл викликачу
// реалізується через заміну стану наступними параметрами:
// value: T - значення, яке необхідно відобразити в ui
// onValueChange: (T) -> Unit - подія, яку виконати для зміни стану

// односпрямований потік даних (Unidirectional Data Flow)
// стан - вниз
// подія - на гору

// властивості state hoisting:
// 1. єдине правдиве джерело
// 2. використання в кількох композбл
// 3. визначення реакції на зміну стану
    // (лямба вираз, який передається в композбл параметром)
// 4. стан може зберігатися будь-де (ViewModel)


// правило підняття стану:

// (стан)\
//   |     \
// (    )\   (змінює)
//   |     \
// (читає)   (чит+змін)

// стан має бути настільки високо, щоб усі, хто його читає або змінює, “діставали” до нього


@Preview
@Composable
fun WaterCounterPreview() {
    MaterialTheme {
        Surface {
            WaterCounter(modifier = Modifier.padding(all = 20.dp),
                glasses = 0,
                onGlassesChanges = {  } )
        }
    }
}