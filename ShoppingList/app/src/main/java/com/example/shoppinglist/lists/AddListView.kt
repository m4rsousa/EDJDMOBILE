package com.example.shoppinglist.lists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.Screen
import com.example.shoppinglist.ui.theme.ShoppingListTheme

@Composable
fun AddListView(modifier: Modifier = Modifier,
                  navController: NavController = rememberNavController()
){
    val viewModel : AddListViewModel = viewModel()
    val state = viewModel.state.value

    Column (modifier = Modifier.fillMaxSize()){
        TextField(
            modifier=Modifier
                .fillMaxSize()
                .padding(16.dp), placeholder = {
                Text("enter list name")},
            value=state.name,
            onValueChange = viewModel::onNameChange
        )
        Button(modifier=Modifier
            .fillMaxSize()
            .padding(16.dp),
            onClick = {
                viewModel.addList()
                navController.popBackStack()
            }) {
            Text("add")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun Screen.AddList(){
    ShoppingListTheme { AddListView()
    }
}