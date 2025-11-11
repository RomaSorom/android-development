package com.example.statefocusedapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.graphics.component1

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    var glasses: Int by remember { mutableStateOf(value = 0) }
    Column(
        modifier = modifier
    ) {
        StateFull()
        TasksList()
    }
}