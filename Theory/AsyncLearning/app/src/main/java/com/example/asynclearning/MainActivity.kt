package com.example.asynclearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.asynclearning.ui.theme.AsyncLearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        // використовувати Mutable/StateFlow (оптимізовані під ViewModel)
        // замість в MutableState, MutableStateList в ViewModel,
        // оскіль вони є інструментами ui'ю


        // асинхронність в compose

            //LaunchedEffect(Unit <-ключ ) { ... } в composable функції
            // LaunchedEffect запускає корутину при вході в композицію
                // корутина не перезапускається при перекомопзиції
                // але запуститься при:
                    // зміні ключа
                    // якщо вийде з композиції та зайде назад



            // viewModelScope.launch { ... } у viewModel
            // життєвий цикл співпалає з цилком ViewModel
            // для роботи з асинхронними операціями у ViewModel



            // val scope = rememberCoroutineScope()
            // життєвий цикл співпалає з цилком composable
            // scope.launch { ... } в composable функції
            // запуск корутини взалежності від події (натискання кнопки)



        // логіка роботи з асинхронністю:
            // відобразити початковий стан (плейсхолдери)
            // до поки асинхронна операція не змінить стан, що призведе до перекомпозиції
            // з актальним станом інтерфейсу
        }
    }
}
