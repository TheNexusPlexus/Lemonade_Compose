package com.example.lemonadecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeApp()
        }
    }
}

@Preview
@Composable
fun LemonadeApp() {
    LemonadeImageAndButton(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun LemonadeImageAndButton(modifier: Modifier = Modifier) {
    var lemonadeState by remember { mutableStateOf(1)}
    var lemonSize by remember {mutableStateOf(-1)}
    var squeezeCount by remember {mutableStateOf(-1)}
    var lemonadeImage by remember { mutableStateOf(R.drawable.lemon_tree)}
    var lemonadeButtonText by remember { mutableStateOf(R.string.lemon_select)}

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(lemonadeButtonText),
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(lemonadeImage),
            contentDescription = "Lemon Tree",
            modifier = Modifier
                .clickable(
                   enabled = true,
                   onClick = {
                       when(lemonadeState) {
                           1 -> {
                               lemonadeState = 2;
                               lemonSize = (2..4).random();
                               squeezeCount = 0
                               lemonadeImage = R.drawable.lemon_squeeze
                               lemonadeButtonText = R.string.lemon_squeeze
                           }
                           2 -> {
                               squeezeCount += 1;
                               lemonSize -= 1;
                               if (lemonSize == 0) {
                                   lemonadeState = 3;
                                   lemonadeImage = R.drawable.lemon_drink
                                   lemonadeButtonText = R.string.lemon_drink
                               }
                           }
                           3 -> {
                               lemonadeState = 4
                               lemonSize = -1
                               lemonadeImage = R.drawable.lemon_restart
                               lemonadeButtonText = R.string.lemon_empty_glass
                           }
                           4 -> {
                               lemonadeState = 1
                               lemonadeImage = R.drawable.lemon_tree
                               lemonadeButtonText = R.string.lemon_select
                           }
                       }
                   }
                )
        )
    }
}