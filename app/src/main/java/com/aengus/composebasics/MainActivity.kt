package com.aengus.composebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aengus.composebasics.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    ComposeBasicsTheme {
        Surface(color = Color.Yellow) {
            content()
        }
    }
}

@Composable
fun MyScreenContent(names: List<String> = List(1000) { "Hello #$it" }) {
    val count = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(names, modifier = Modifier.weight(1f))
        Counter(count.value,
            onCountChange = { newValue -> count.value = newValue })
    }
}

@Composable
fun Counter(count: Int, onCountChange: (Int) -> Unit) {
    Button(
        onClick = { onCountChange(count + 1) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count >= 5) Color.Green else Color.Red
        )
    ) {
        Text(text = "I was click $count times")
    }
}

@Composable
fun NameList(names:List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
        items(items = names) { name ->
            Greeting(name = name)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}