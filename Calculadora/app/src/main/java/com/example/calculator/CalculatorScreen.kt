import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.CalcButton
import com.example.calculator.Greeting
import com.example.calculator.ui.theme.CalculatorTheme
import java.math.BigDecimal


@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    //var backButtonColor = ButtonDefaults.run{ buttonColors(Purple40) }
    var display by remember { mutableStateOf("0") }
    var op1 by remember { mutableStateOf(BigDecimal(0.0)) }
    var operation by remember { mutableStateOf("") }

    fun onButtonClick(clickedButtonText: String) {
        when(clickedButtonText){
            "AC"->{
                display="0"
                op1=BigDecimal(0.0)
                operation=""
            }
            "+/-"->{
                // Toggle sign
                display = if (display.startsWith("-")) display.substring(1) else "-$display"
            }
            "%"->{
                // Calculate percentage
                val percentage = display.toDoubleOrNull() ?: 0.0
                display = (percentage / 100).toString()
            }
            "⌫"->{
                if (display.isNotEmpty()) {
                    display = display.dropLast(1)
                    if (display.isEmpty()) {
                        display = "0"
                    }
                    }
            }
            "."->{
                if(!display.contains(".")){
                    display+="."
                }
            }
            "+","-","x","÷"->{
                op1 = BigDecimal(display)
                operation = clickedButtonText
                display = ""
            }
            "="->{
                val op2 = BigDecimal(display)
                if (operation.isNotEmpty()) {
                    try {
                        op1 = when (operation) {
                            "+" -> op1.add(op2)
                            "-" -> op1.subtract(op2)
                            "x" -> op1.multiply(op2)
                            "÷" -> op1.divide(op2)
                            else -> BigDecimal(0.0)
                        }
                    } catch (e: ArithmeticException) {
                        display = "Error"
                    }
                    display = op1.toString()
                }
                operation=""
            }
            else -> {
                if (display == "0" && clickedButtonText.all { it.isDigit() }) {
                    display = clickedButtonText
                } else {
                    display += clickedButtonText
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedTextField(
            value = display,
            onValueChange = { /* Handle input if needed */ },
            readOnly = true, // Make it read-only to prevent direct editing
            modifier = Modifier
                .width(500.dp).height(250.dp)
                .padding(20.dp,50.dp),
            textStyle = TextStyle(fontSize = 80.sp, textAlign = TextAlign.Right) // Customize text style
        )
//        Text(
//            modifier = Modifier
//                .fillMaxSize()
//                .weight(1f),
//            textAlign = TextAlign.Right,
//            text = display,
//            style = MaterialTheme.typography.displayLarge,
//        )
        Row(modifier = Modifier.weight(1f)) {
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "AC",
                isOperation = true,
                onClick = { onButtonClick("AC") })
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "+/-",
                isOperation = true,
                onClick = { onButtonClick("+/-") })
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "%",
                isOperation = true,
                onClick = { onButtonClick("%") })
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "÷",
                isOperation = true,
                onClick = { onButtonClick("÷") })
        }
        Row(modifier = Modifier.weight(1f)) {
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "7",
                isOperation = false,
                onClick = { onButtonClick("7") })
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "8",
                isOperation = false,
                onClick = { onButtonClick("8") })
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "9",
                isOperation = false,
                onClick = { onButtonClick("9") })
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "x",
                isOperation = true,
                onClick = { onButtonClick("x") })
        }
        Row(modifier = Modifier.weight(1f)) {
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "4",
                isOperation = false,
                onClick = { onButtonClick("4") })
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "5",
                isOperation = false,
                onClick = { onButtonClick("5") })
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "6",
                isOperation = false,
                onClick = { onButtonClick("6") })
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "-",
                isOperation = true,
                onClick = { onButtonClick("-") })
        }
        Row(modifier = Modifier.weight(1f)) {
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "1",
                isOperation = false,
                onClick = { onButtonClick("1") })
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "2",
                isOperation = false,
                onClick = { onButtonClick("2") })
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "3",
                isOperation = false,
                onClick = { onButtonClick("3") })
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "+",
                isOperation = true,
                onClick = { onButtonClick("+") })

        }
        Row(modifier = Modifier.weight(1f)) {
            CalcButton(
                modifier = Modifier.weight(1f),
                label = ".",
                isOperation = false,
                onClick = { onButtonClick(".") })
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "0",
                isOperation = false,
                onClick = { onButtonClick("0") })
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "⌫",
                isOperation = false,
                onClick = { onButtonClick("⌫") })
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "=",
                isOperation = true,
                onClick = { onButtonClick("=") })
        }
    }
}

@Composable
fun CalculatorPreview() {
    CalculatorTheme {
        Greeting("Android")
    }
}