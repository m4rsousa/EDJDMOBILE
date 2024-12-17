package com.example.shoppinglist.lists.items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.R
import com.example.shoppinglist.Screen

@Composable
fun ListItemsView(
    modifier: Modifier = Modifier,
    listId : String,
    navController: NavController = rememberNavController()
){
    val viewModel : ListItemsViewModel = viewModel()
    val state = viewModel.state.value

    LaunchedEffect (key1 = true){
        viewModel.getItems(listId)
    }

    Box(modifier = modifier
        .fillMaxSize()
        .padding(16.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Items",
                fontSize = 30.sp
            )
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                itemsIndexed(
                    items = state.items
                ) { index, item ->
                        ItemRomView(item = item) {
                            viewModel
                                .toggleItemChecked(
                                    listId = listId,
                                    item = item
                                )
                        }

                }
            }
        }

        FilledIconButton(
            modifier = modifier
                .padding(16.dp)
                .size(64.dp)
                .align(Alignment.BottomEnd),
            colors = IconButtonDefaults.filledIconButtonColors().copy(
                containerColor = Color(0xFF6650a4),
                contentColor = Color.White
            ),
            onClick = {
                navController.navigate(Screen.AddItem.route.replace("{listId}", listId))
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                modifier = Modifier.size(32.dp),
                contentDescription = "Add"
            )
        }
        IconButton(
            modifier = modifier
                .padding(16.dp)
                .size(64.dp)
                .align(Alignment.BottomStart),
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

//@Preview(showBackground = true)
//@Composable
//fun ListItemsViewPreview(){
//    ShoppingListTheme {
//        ListItemsView(listId = "")
//    }
//}