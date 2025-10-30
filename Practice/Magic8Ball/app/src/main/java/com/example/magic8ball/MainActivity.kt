package com.example.magic8ball

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.magic8ball.ui.theme.Magic8BallTheme

import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Magic8BallTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    CenteredBall(modifier = Modifier.padding(paddingValues = innerPadding))
//                }
                CenteredBall()
            }
        }
    }
}

@Composable
fun CenteredBall(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.onBackground),
        contentAlignment = Alignment.Center
    ) {
        Ball()
    }
}

@Composable
fun Ball() {
    var shakeBall: () -> Unit

    Box(
        modifier = Modifier
            .size(width = 300.dp, height = 300.dp)
            .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(width = 170.dp, height = 170.dp)
                .background(color = Color.White, shape = CircleShape)
                .clickable { shakeBall() },
            contentAlignment = Alignment.Center
        ) {
            Answer { shakeFun -> shakeBall = shakeFun }
        }
    }
}

@Preview
@Composable
fun PreviewBall() {
    Magic8BallTheme {
        Ball()
    }
}

@Composable
fun Answer(passShakeFun: (() -> Unit) -> Unit) {
    var answer: String by remember { mutableStateOf(value = shakeBall()) }
    passShakeFun { answer = shakeBall() }
    Text(
        text = answer,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .widthIn(max = 150.dp)
    )
}

fun shakeBall(): String {
    val answerIndex: Int = Random.nextInt(from = 0, until = answers.count())
    val answer: String = answers[answerIndex]
    return answer
}

val answers: List<String> = listOf(
    "Так",
    "Безсумнівно",
    "Можеш покладатися на це",
    "Найвірогідніше",
    "Так, без сумнівів",
    "Знаки кажуть «так»",
    "Ймовірно",
    "Так точно",
    "Безперечно",
    "Очевидно",

    "Спробуй пізніше",
    "Запитай пізніше",
    "Не можу зараз передбачити",
    "Сконцентруйся і спитай знову",
    "Поки що не ясно",

    "Ні",
    "Не розраховуй на це",
    "Важко сказати",
    "Мої джерела кажуть «ні»",
    "Навряд чи"
)