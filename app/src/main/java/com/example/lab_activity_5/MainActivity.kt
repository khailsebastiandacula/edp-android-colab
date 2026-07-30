package com.example.lab_activity_5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MaterialTheme {

                Surface {

                    ReactiveScreen()

                }
            }
        }
    }
}


@Composable
fun ReactiveScreen() {

    var count by remember {
        mutableStateOf(0)
    }


    var name by rememberSaveable {
        mutableStateOf("")
    }


    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center

    ) {


        Text(

            text = if(name.isBlank())
                "Hello, stranger!"
            else
                "Hello, $name!",

            fontSize = 26.sp,

            fontWeight = FontWeight.Bold

        )


        Spacer(
            modifier = Modifier.height(16.dp)
        )


        OutlinedTextField(

            value = name,

            onValueChange = {
                name = it
            },

            label = {
                Text("Enter your name")
            }

        )


        Spacer(
            modifier = Modifier.height(32.dp)
        )


        CounterControls(

            count = count,

            onIncrement = {
                count++
            },

            onDecrement = {
                count--
            },

            onReset = {
                count = 0
            }

        )

    }

}



@Composable
fun CounterControls(

    count: Int,

    onIncrement: () -> Unit,

    onDecrement: () -> Unit,

    onReset: () -> Unit

) {


    Column(

        horizontalAlignment = Alignment.CenterHorizontally

    ) {


        Text(

            text = "Count: $count",

            fontSize = 24.sp

        )


        Spacer(
            modifier = Modifier.height(16.dp)
        )


        Row(

            horizontalArrangement = Arrangement.spacedBy(12.dp)

        ) {


            Button(
                onClick = onDecrement
            ) {

                Text("–")

            }



            Button(
                onClick = onReset
            ) {

                Text("Reset")

            }



            Button(
                onClick = onIncrement
            ) {

                Text("+")

            }

        }

    }

}