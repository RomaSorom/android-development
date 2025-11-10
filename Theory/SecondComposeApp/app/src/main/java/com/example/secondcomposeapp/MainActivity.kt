package com.example.secondcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.secondcomposeapp.ui.theme.SecondComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SecondComposeAppTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        SafeArea(
            modifier = Modifier.padding(paddingValues = innerPadding)
        )
    }
}

@Composable
fun SafeArea(modifier: Modifier = Modifier) {
    val list: List<String> = List(100) { "$it" }
    var showOnboarding: Boolean by rememberSaveable { mutableStateOf(true) }
    if (showOnboarding) {
        OnboardingScreen(modifier = modifier) { showOnboarding = false }
    } else {
        ListScreen(modifier = modifier, list = list) { showOnboarding = true }
    }
}

@Composable
fun Greeting(name: String) {
    var isExpanded: Boolean by rememberSaveable { mutableStateOf(false) }

    val extraPadding: Dp by animateDpAsState(
        if (isExpanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        color = MaterialTheme.colorScheme.primary
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
                .padding(all = 24.dp)
        ) {
            Column(
                modifier = Modifier.padding(bottom = extraPadding.coerceAtLeast(0.dp))
                    //.fillMaxWidth(),
            ) {
                Text(text = "Hello,")
                Text(text = "$name!", style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                ))
            }
            ElevatedButton(
                onClick = { isExpanded = !isExpanded },
                //modifier = Modifier.padding(24.dp)
            ) {
                Text(text = if (isExpanded) "Show less" else "Show more")
            }
        }
    }
}

@Composable
fun ListScreen(modifier: Modifier = Modifier, list: List<String>, showOnboarding: () -> Unit) {
    Column(modifier = modifier) {
        ElevatedButton(
            onClick = { showOnboarding() }
        ) {
            Text("Back")
        }
        LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
            items(items = list) { name ->
                Greeting(name = name)
            }
        }
    }
}

@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, hideOnboarding: () -> Unit) {
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome screen")
            Button(
                onClick = { hideOnboarding() }
            ) {
                Text(text = "Next screen")
            }
        }
    }
}