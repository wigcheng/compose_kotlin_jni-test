package com.example.compose_test

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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

                    var DropdownGpioBank = createDropDownList()
                    var DropdownGpioLine = createDropDownList()
                    var DropdownGpioDirection = createDropDownList()
                    Column {
                        CardDemo()
                        //Greeting(stringFromJNI())
                        Greeting("Select GPIO BANK")
                        DropdownGpioBank.DropdownCreate(5, createDropDownList.listType.GPIO_BANK)
                        Greeting("Select GPIO LINE")
                        DropdownGpioLine.DropdownCreate(5, createDropDownList.listType.GPIO_LINE)
                        Greeting("Select Direction")
                        DropdownGpioDirection.DropdownCreate(5, createDropDownList.listType.GPIO_DIR)
                        Row (horizontalArrangement = Arrangement.spacedBy(8.dp),){
                            ButtonWithColor()
                            ButtonWithColor()
                        }

                    }

                }
            }
        }
    }

    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'myapplication' library on application startup.
        init {
            System.loadLibrary("compose_test")
        }
    }
}

class createDropDownList{

    var index = 0

    enum class listType {
        GPIO_BANK,
        GPIO_LINE,
        GPIO_DIR
    }

    @Composable
    fun DropdownCreate(total: Int, dropdown_type: listType) {
        var expanded by remember { mutableStateOf(false) }

        var selectedIndex by remember { mutableStateOf(0) }
        var type_str: String = ""
        val items = ArrayList<String>()

        if(dropdown_type == listType.GPIO_BANK) {
            type_str = "gpiochip"
        }else if(dropdown_type == listType.GPIO_LINE) {
            type_str = "Num "
        }

        if(dropdown_type == listType.GPIO_DIR) {
            items.add("OUT")
            items.add("IN")
        } else {
            for (num in 1..total) {
                items.add(type_str + (num - 1))
            }
        }
        val disabledValue = "gpiochip4"
        Row {
            Box(modifier = Modifier
                .size(45.dp).clickable(onClick = { expanded = true })
                .clip(RectangleShape).background(Gray)) {
                Image(painter = painterResource(R.drawable.ic_down_chevron), contentDescription = "down_chevron")
            }

            Text(
                items[selectedIndex],
                modifier = Modifier.fillMaxWidth().clickable(onClick = { expanded = true })
                    .background(Gray)
            )

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
                        append("libgpiod base GPIO control panel")
                    }
                }
            )
        }
    }
}


@Composable
fun Greeting(name: String) {
    Row {
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

@Composable
fun ButtonWithColor(){
    val context = LocalContext.current
    Button(onClick = {
        //your onclick code
        Toast.makeText(
            context,
            "I am a toast message",
            Toast.LENGTH_SHORT
        ).show()
    },
        colors = ButtonDefaults.buttonColors(backgroundColor = DarkGray))

    {
        Text(text = "Run",color = White,  fontSize = 40.sp)
    }
}