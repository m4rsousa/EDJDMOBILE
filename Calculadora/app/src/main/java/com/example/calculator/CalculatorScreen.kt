import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.Greeting
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.Purple40


@Composable
fun CalculatorScreen(modifier : Modifier = Modifier) {
    var backButtonColor = ButtonDefaults.run{ buttonColors(Purple40) }
    var num by remember { mutableStateOf("") }
    var show by remember { mutableStateOf("") }
    val buttonClick:()->Unit= {show="$num"}
    Column (modifier = modifier.padding(16.dp)
        .fillMaxSize(),verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "0")
        Row {
            Button(onClick = buttonClick) {
                Text(text = "7")
            }
            Button(onClick = buttonClick) {
                Text(text = "8")
            }
            Button(onClick = buttonClick) {
                Text(text = "9")
            }
            Button(onClick = buttonClick,colors=backButtonColor,) {
                Text(text = "x")
            }

        }
        Row {
            Button(onClick = buttonClick) {
                Text(text = "4")
            }
            Button(onClick = buttonClick) {
                Text(text = "5")
            }
            Button(onClick = buttonClick) {
                Text(text = "6")
            }
            Button(onClick = buttonClick,colors=backButtonColor,) {
                Text(text = "-")
            }
        }
        Row {
            Button(onClick = buttonClick) {
                Text(text = "1")
            }
            Button(onClick = buttonClick) {
                Text(text = "2")
            }
            Button(onClick = buttonClick) {
                Text(text = "3")
            }
            Button(onClick = buttonClick,colors=backButtonColor,) {
                Text(text = "+")
            }
        }
        Row {
            Button(onClick = buttonClick ) {
                Text(text = "0")
            }
            Button(onClick = buttonClick,colors=backButtonColor,) {
                Text(text = "=")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    CalculatorTheme {
        Greeting("Android")
    }
}