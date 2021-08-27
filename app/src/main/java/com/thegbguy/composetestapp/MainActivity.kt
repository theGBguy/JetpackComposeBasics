package com.thegbguy.composetestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thegbguy.composetestapp.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
//                Greeting("Android")
                MyScreenContent()
            }
        }
    }
}

private val DarkColors = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
)

private val LightColors = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200
)

@Composable
fun MyAppCustomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }
    MaterialTheme(colors = colors) {
        content()
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(
        onClick = { updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
        )
    ) {
        Text(text = "I have been clicked ${count} times")
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Greeting(name)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun MyScreenContent(names: List<String> = List(100) { "Hello Word $it" }) {
    val counterState = remember {
        mutableStateOf(0)
    }

    Column(modifier = Modifier.fillMaxHeight()) {
//        Column(modifier = Modifier.weight(1f)) {
//        Greeting("Android")
//        Divider(color = Color.Black)
//        Greeting("there")
//            for (name in names) {
//                Greeting(name)
//                Divider(color = Color.Black)
//            }
//        }
        NameList(names = names, modifier = Modifier.weight(1f))
//            Divider(color = Color.Transparent, thickness = 32.dp)
        Counter(
            count = counterState.value,
            updateCount = { newCount ->
                counterState.value = newCount
            }
        )
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    ComposeTestAppTheme {
        Surface(color = Color.Yellow) {
            content()
        }
    }
}

@Composable
fun Greeting(name: String) {
    var isSelected by remember {
        mutableStateOf(false)
    }
    val backgroundColor by animateColorAsState(
        if (isSelected) MaterialTheme.colors.primary else Color.Transparent
    )

    Surface(color = Color.Yellow) {
        Text(
            text = "$name!",
            modifier = Modifier
                .padding(24.dp)
                .background(backgroundColor)
                .clickable { isSelected = !isSelected }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
//        Greeting("Android")
        MyScreenContent()
    }
}