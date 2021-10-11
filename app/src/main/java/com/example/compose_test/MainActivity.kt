package com.example.compose_test

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_test.ui.theme.Compose_testTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_testTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        CardDemo()
                        Greeting("Select GPIO Bank")
                        DropdownCreate()
                        Greeting("Select GPIO LINE")
                        DropdownCreate()
                        Greeting("Select Direction")
                        DropdownCreate()
                    }

                }
            }
        }
    }
}

@Composable
fun DropdownCreate() {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("gpiochip0", "gpiochip1", "gpiochip2", "gpiochip3", "gpiochip4", "gpiochip5")
    val disabledValue = "gpiochip5"
    var selectedIndex by remember { mutableStateOf(0) }

        Text(items[selectedIndex],modifier = Modifier.fillMaxWidth().clickable(onClick = { expanded = true }).background(Gray))
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth().background(White),
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }) {
                    val disabledText = if (s == disabledValue) {
                        " (Disabled)"
                    } else {
                        ""
                    }
                    Text(text = s + disabledText, fontSize = 40.sp)
                }
            }
        }
}

@Composable
fun CardDemo() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable{ },
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                buildAnnotatedString {
                    append("welcome to ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W900)
                    ) {
                        append("Jetpack Compose Playground")
                    }
                }
            )
            Text(
                buildAnnotatedString {
                    append("Now you are in the ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W900)) {
                        append("Card")
                    }
                    append(" section")
                }
            )
        }
    }
}


@Composable
fun Greeting(name: String) {
    Row {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Red)
        ){
            Image(painter = painterResource(R.drawable.ic_launcher_foreground), contentDescription = "test", contentScale = ContentScale.Crop)
        }
        Text(text = "$name")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_testTheme {
        Greeting("Android")
    }
}