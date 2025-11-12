package com.example.statefocusedapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    text: String,
    onCloseTask: () -> Unit
) {
    Surface(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = checked,
                    onCheckedChange = { bool -> onCheckedChange(bool)} )
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { onCloseTask() }
                )
            }
        }
    }
}

@Composable
fun TaskItem(text: String,
             onCloseTask: () -> Unit,
             modifier: Modifier = Modifier) {
    var checkedState: Boolean by rememberSaveable { mutableStateOf(false) }

    TaskItem(checked = checkedState,
        onCheckedChange = { bool -> checkedState = bool },
        text = text) { onCloseTask() }
}

@Preview
@Composable
fun TaskItemPreview() {
    MaterialTheme {
        Surface {
            TaskItem(
                modifier = Modifier.padding(all = 20.dp),
                text = "New Task Item",
                checked = true,
                onCheckedChange = { },
                onCloseTask = {  }
            )
        }
    }
}