package com.example.firstcomposeapp

import android.app.people.ConversationStatus
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstcomposeapp.ui.theme.FirstComposeAppTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // composable-функції - основа jetpack compose
        // опис ui програмно:
        // - зовнішній вигляд
        // - дані для використання
        // @Composable - анотація
        setContent {
            FirstComposeAppTheme { // Тема застосунку
                // стилізує дочірні елементи темою, яка описана в Theme.kt
                // MaterialTheme надає доступ до значень елементів теми застосунку
                Surface(Modifier.fillMaxSize()) {
                    // MessageCard(Message("Roma", "Hello, world!")) // виклик compose-fun
                    Conversation(conversation)
                }
            }
            // Text("Hello, JetpackCompose!") // Text є composable-функцією
            // compose-fun викликається тільки з іншої compose-fun
            // compose-fun перетворюється на елемент ui при компіляції
        }
    }
}

@Composable
fun MessageCard(msg: Message) { // визначення (define) compose-fun
    Row(Modifier.padding(8.dp)) { // modifiers для зміни оформлення/налаштування compose-fun
        Image(
            painterResource(R.drawable.profile_picture),
            "Profile picture",
            Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        Spacer(Modifier.width(8.dp))

        // isExpanded - локальний стан compose-fun
        // (у нашому прикладі - Text composable)
        var isExpanded by remember { mutableStateOf(false) } // відстеження стану ui елемента
        // compose-fun, які використовують цей стан,
        // і дочірні елементи цієї функції
        // автоматично перемалюються при його оновленні
        // - це перекомозування (recomposition)

        val surfaceColor by animateColorAsState( // by - оператор делегування
            // значення surfaceColor делегується animateColorAsState
            if (isExpanded) MaterialTheme.colorScheme.primary else
                MaterialTheme.colorScheme.secondary
        )

        Column(Modifier.clickable {isExpanded = !isExpanded}) { // column composable
            Text(msg.author, color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.titleSmall)
            Spacer(Modifier.height(4.dp))
            Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier.animateContentSize().padding(1.dp)) {
                Text(msg.body, Modifier.padding(4.dp), style = MaterialTheme.typography.titleSmall,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1)
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
// перегляд compose-fun без збірки проєкту
// фунація повинна бути пез параметів
@Composable
fun PreviewMessageCard() {
    FirstComposeAppTheme {
        Surface {
            MessageCard(Message("Roma", "Hello, Compose!"))
        }
    }
}

// ui ієрархічний -> певна compose-fun викликає іншу compose-fun

data class Message(val author: String, val body: String)

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn { // відображаються лише ті елементи, які видно на екрані
        // оптимізований під великі списки
        items(messages) {message ->
            MessageCard(message)
        }
    }
}

val conversation: List<Message> = listOf(
    Message("Roma", "Hello"),
    Message("Sorom", "Hello, sorom"),
    Message("peacemaker", "hello, world"),
    Message("rpu", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque consequat lorem sed dapibus interdum. Proin.")
)

@Preview
@Composable
fun PreviewConversation() {
    FirstComposeAppTheme {
        Surface {
            Conversation(conversation)
        }
    }
}