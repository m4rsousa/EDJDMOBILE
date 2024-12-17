package com.example.shoppinglist.lists.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.R
import com.example.shoppinglist.Screen
import com.example.shoppinglist.ui.theme.ShoppingListTheme

@Composable
fun AddItemView(modifier: Modifier = Modifier,
                navController: NavController = rememberNavController(),
                listId : String = ""
){
    val viewModel : AddItemViewModel = viewModel()
    val state = viewModel.state.value


    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Add a new item!",
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier=Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = {
                Text("Enter item name")
            },
            value = state.name,
            onValueChange = viewModel::onNameChange
        )
        TextField(
            modifier=Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = {
                Text("Amount")
            },
            value = state.qtd,
            onValueChange = viewModel::onQtdChange,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )
        Button(
            modifier = Modifier
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6650a4),
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                ),
            onClick = {
                viewModel.addItem(listId)
                navController.popBackStack()
            }
        ) { Text("Add") }
        IconButton(
            modifier = modifier
                .padding(16.dp)
                .size(64.dp)
                .align(Alignment.Start),
            colors = IconButtonDefaults.iconButtonColors().copy(
                contentColor = Color(0xFF6650a4)
            ),
            onClick = {
                navController.popBackStack()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                modifier = Modifier.size(32.dp),
                contentDescription = "back"
            )
        }
    }

}



@Preview(showBackground = true)
@Composable
fun Screen.AddList(){
    ShoppingListTheme { AddItemView()
    }
}