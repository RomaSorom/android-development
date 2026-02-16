package com.example.composecomeback

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ClicksCounter(
    clicksNum: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(clicksNum.toString())
        Button(
            onClick = onClick
        ) {
            Text("+")
        }
    }
}

@Preview
@Composable
fun ClicksCounterPreview() {
    ClicksCounter(3) { }
}