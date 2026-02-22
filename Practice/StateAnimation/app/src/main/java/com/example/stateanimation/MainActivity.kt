package com.example.stateanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stateanimation.ui.theme.StateAnimationTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StateAnimationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AnimatedSurface(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AnimatedSurface(modifier: Modifier = Modifier) {
    var isTransparent by remember { mutableStateOf(false) }
    val alpha by myAnimateAlphaAsState(
        if (isTransparent) 1.0f else 0.2f
    )

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.size(size = 60.dp).alpha(alpha),
            color = MaterialTheme.colorScheme.primary
        ) {

        }
        Button(
            onClick = { isTransparent = !isTransparent }
        ) { Text(isTransparent.toString()) }
    }
}

@Preview
@Composable
fun AnimatedSurfacePreview() {
    AnimatedSurface()
}

@Composable
fun myAnimateAlphaAsState(target: Float): State<Float> {
    val alpha = remember { mutableStateOf(target) }

    LaunchedEffect(target) {

        val step = if (target == 0.2f) -0.1f else 0.1f

        while (kotlin.math.abs(alpha.value - target) > 0.01f) {


            alpha.value += step
            delay(16)
        }

    }

    return alpha
}