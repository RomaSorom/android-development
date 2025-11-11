package com.example.statefocusedapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun StateFull(
    modifier: Modifier = Modifier
) {
    var glasses: Int by remember { mutableStateOf(value = 0) }
    WaterCounter(modifier = modifier,
        glasses = glasses) { increment -> glasses += increment }
}