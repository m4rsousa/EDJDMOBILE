package com.example.shoppinglist.lists

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
fun ListListsView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {
    val viewModel : ListListsViewModel = viewModel()
    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        viewModel.getLists()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            Text(
                text = "Your Lists",
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                itemsIndexed(
                    items = state.listItemsList,
                    key = { _, item -> item.docId!! }
                ) {  _, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF6650a4))
                            .clickable {
                                navController.navigate(
                                    Screen.ListItems.route.replace("{listId}", item.docId!!)
                                )
                            }
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(16.dp),
                            fontSize = 25.sp,
                            text = item.name ?: ""
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
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
                navController.navigate(Screen.AddList.route)
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


@Preview(showBackground = true)
@Composable
fun ListListsPreview(){
    ShoppingListTheme { ListListsView()
    }
}