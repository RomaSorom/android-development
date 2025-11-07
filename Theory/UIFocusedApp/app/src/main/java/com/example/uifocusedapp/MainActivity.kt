package com.example.uifocusedapp

import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uifocusedapp.ui.theme.UIFocusedAppTheme
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UIFocusedAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        SearchBar()
                    }
                ) { innerPadding ->
                    Column {
                        MountainsRow(modifier = Modifier.padding(innerPadding))
                        MyCardGrid()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UIFocusedAppTheme {
        Greeting("Android")
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
//        colors = TextFieldDefaults.colors(
//            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
//            focusedContainerColor = MaterialTheme.colorScheme.surface
//        ),
        placeholder = {
            Text(
//                R.string.search_placeholder
                "Search"
            )
        },
        modifier = modifier.fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Preview
@Composable
fun SearchBarPreview() {
    UIFocusedAppTheme {
        SearchBar(modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun Mountain(
    modifier: Modifier = Modifier,
    @DrawableRes drawable: Int,
    @StringRes text: Int
) {
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(88.dp)
                .clip(CircleShape)
        )
        Text(
            stringResource(text),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
    }
}

@Preview
@Composable
fun MountainPreview() {
    Mountain(
        drawable = R.drawable.mountain,
        text = R.string.app_name,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun MyCard(
    modifier: Modifier = Modifier,
    @DrawableRes drawable: Int,
    @StringRes text: Int
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .height(80.dp)
            .width(255.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = stringResource(text),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun MyCardPreview() {
    MaterialTheme {
        MyCard(
            drawable = R.drawable.mountain,
            text = R.string.app_name,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun MountainsRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(9) {
            Mountain(
                drawable = R.drawable.mountain,
                text = R.string.app_name
            )
        }
    }
}

@Preview
@Composable
fun MountainsRowPreview() {
    MaterialTheme {
        MountainsRow(modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun MyCardGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .height(168.dp)
    ) {
        items(9) {
            MyCard(
                drawable = R.drawable.mountain,
                text = R.string.app_name
            )
        }
    }
}

@Preview
@Composable
fun MyCardGridPreview() {
    MaterialTheme {
        MyCardGrid()
    }
}