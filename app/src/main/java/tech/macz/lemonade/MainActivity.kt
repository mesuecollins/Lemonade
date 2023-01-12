package tech.macz.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.macz.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {
    var currentStep by remember { mutableStateOf(1) }
    // Number of times the lemon needs to be squeezed to turn into a glass of lemonade
    var squeezeCount by remember { mutableStateOf(0) }
    val imageResource = when(currentStep) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val textResource = when(currentStep) {
        1 -> R.string.select_lemon
        2 -> R.string.squeeze
        3 -> R.string.drink
        else -> R.string.startAgain
    }
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        color = MaterialTheme.colors.background
    ) {
        Column (modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            Text(text = stringResource(textResource), fontSize = 20.sp)
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = stringResource(id = R.string.Lemon_tree_content_description),
                modifier = Modifier
                    .wrapContentSize()
                    .clickable {
                        //squeezeCount = (2..4).random()
                        if (currentStep == 1){
                            currentStep++
                            squeezeCount = (2..6).random()
                        }
                        else if (currentStep==2 && squeezeCount==0){
                            currentStep ++
                        }
                        else if (currentStep==2 && squeezeCount!=0){
                            squeezeCount--
                            currentStep = 2
                        }

                        else if (currentStep==3){
                            currentStep++
                        }
                        else {
                            currentStep=1
                        }
                    }
                    .border(
                        BorderStroke(2.dp, Color(105, 205, 216)),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}