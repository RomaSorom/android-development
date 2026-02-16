package com.example.composecomeback

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    myViewModel: MyViewModel = viewModel()
) {
    val clicksNum by myViewModel.clicksNum.collectAsState()

    ClicksCounter(
        clicksNum,
        modifier
    ) {
        myViewModel.incr()
    }
}