package com.example.statefocusedapp

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

fun getTasksList() = List(30) { i -> Task(i, "Task # $i") }

@Composable
fun TasksList(
    modifier: Modifier = Modifier,
    list: List<Task> = remember { getTasksList() }
) {
    LazyColumn(modifier = modifier) {
        items(list) {
            task -> TaskItem(task.label)
        }
    }
}