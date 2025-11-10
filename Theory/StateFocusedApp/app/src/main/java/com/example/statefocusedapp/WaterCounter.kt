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
    modifier: Modifier = Modifier
) {
    var glasses: Int by remember { mutableStateOf(value = 0) }
    // var showTask: Boolean by remember { mutableStateOf(value = true) } цей стан завжди зберігається в композиції,
    // оскільки remember завжди викликається
    Column(
        modifier = modifier
    ) {
        if (glasses > 0) { // якщо remember не виконуться під час композиції, композ забуває стан,
            // який цей remember зберігав в композиції
            var showTask: Boolean by remember { mutableStateOf(value = true) }
            if (showTask) {
                TaskItem(
                    text = "Some text",
                ) { showTask = false }
            }
            Text(
                text = "Glasses consumed: $glasses",
            )
        }
        Row {
            Button(
                onClick = { glasses++ },
                enabled = glasses < 10
            ) {
                Text(
                    text = "+1 glass"
                )
            }
            Button(
                onClick = {
                    glasses = 0
                }
            ) {
                Text("Clear count")
            }
        }
    }
}

@Preview
@Composable
fun WaterCounterPreview() {
    MaterialTheme {
        Surface {
            WaterCounter(modifier = Modifier.padding(all = 20.dp))
        }
    }
}