package com.example.myapplication.ui.navigationBars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.Screen

data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen,
)

@Composable
fun BottomBarNav(navController: NavHostController) {
    var selectedItemIndex by remember { mutableStateOf(0) } // State to track selected item
    val bottomNavItems = listOf(
        BottomNavItem(
            title = "Home",
            icon = Icons.Filled.Home,
            screen = Screen.Latest
        ),
        BottomNavItem(
            title = "Health",
            icon = ImageVector.vectorResource(id = R.drawable.health) ,
            screen = Screen.Health
        ),
        BottomNavItem(
            title = "Politics",
            icon = ImageVector.vectorResource(id = R.drawable.politics),
            screen = Screen.Politics
        ),
        BottomNavItem(
            title = "Sports",
            icon = ImageVector.vectorResource(id = R.drawable.sports),
            screen = Screen.Sports
        )
    )
    NavigationBar(containerColor = Color(0xFF221931))
    {
        bottomNavItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(
                        item.screen.route
                    )
                },
                label = {
                    Text(text = item.title,fontSize = 16.sp)
                },
                alwaysShowLabel = true,
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex) {
                            item.icon
                        } else item.icon,
                        contentDescription = item.title
                    )

                }
            )
        }
    }
}

