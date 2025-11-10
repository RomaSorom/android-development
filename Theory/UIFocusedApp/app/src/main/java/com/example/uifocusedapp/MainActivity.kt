package com.example.uifocusedapp

import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.os.Bundle
import android.view.Surface
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
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val windowSize: WindowSizeClass = calculateWindowSizeClass(this)
            UIFocusedAppTheme {
                MyApp(windowSize = windowSize)
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
        modifier = modifier
            .fillMaxWidth()
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
            modifier = Modifier
                .size(88.dp)
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

@Composable
fun SlotBased(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            stringResource(title),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Preview
@Composable
fun SlotBasedPreview() {
    MaterialTheme {
        SlotBased(
            title = R.string.app_name,
        ) {
            MountainsRow()
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        SearchBar(
            modifier = Modifier
                .padding(16.dp)
        )
        SlotBased(
            title = R.string.app_name,
        ) {
            MountainsRow()
        }
        SlotBased(
            title = R.string.app_name
        ) {
            MyCardGrid()
        }
    }
}

@Composable
fun HomeScreenPortrait() {
    Scaffold(
        bottomBar = {
            MyBottomNav()
        }
    ) { innerPadding ->
        HomeScreen(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview
@Composable
fun PortraitPreview() {
    MaterialTheme {
        HomeScreenPortrait()
    }
}

@Preview(heightDp = 180)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(

        )
    }
}

@Composable
fun MyBottomNav(
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    "Item1"
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    "Item2"
                )
            }
        )
    }
}

@Composable
fun MyNavRail(
    modifier: Modifier = Modifier
) {
    NavigationRail(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceVariant

    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                selected = true,
                onClick = {},
                icon = {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        "Item1"
                    )
                }
            )
            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )
            NavigationRailItem(
                selected = false,
                onClick = {},
                icon = {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        "Item2"
                    )
                }
            )
        }
    }
}

@Composable
fun HomeScreenLandscape(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        Row {
            MyNavRail()
            HomeScreen()
        }
    }
}

@Preview
@Composable
fun LandscapePreview() {
    MaterialTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreenLandscape()
        }
    }
}

@Composable
fun MyApp(windowSize: WindowSizeClass) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            HomeScreenPortrait()
        }
        WindowWidthSizeClass.Expanded -> {
            HomeScreenLandscape()
        }
    }
}