package com.example.shoppinglist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.ui.theme.ShoppingListTheme

// LoginView.kt
@Composable
fun LoginView(
    modifier: Modifier = Modifier,
    onLoginSuccess : ()->Unit = {},
    navController : NavController

) {
    val viewModel: LoginViewModel = viewModel()
    val state = viewModel.state.value

    LoginViewContent(
        modifier = modifier,
        state = state,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = { viewModel.onLoginClick {
            onLoginSuccess()
        }},
        navController = navController // Pass the NavController
    )

}
@Composable
fun LoginViewContent(modifier: Modifier = Modifier,
                     state: LoginState,
                     onEmailChange : (String)->Unit ={},
                     onPasswordChange : (String)->Unit ={},
                     onLoginClick: () -> Unit = {},
                     navController : NavController = rememberNavController()
) {

    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){  Text(modifier = Modifier,text="WELCOME BACK!")
            Spacer(modifier = Modifier.height(30.dp))
            TextField(value = state.email,
                onValueChange = {
                   onEmailChange(it)
                },
                placeholder = {
                    Text("email")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(value = state.password,
                onValueChange = {
                   onPasswordChange(it)
                },
                placeholder = {
                    Text("password")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                   onLoginClick()
                },
                content = {
                    Text("Login")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(modifier = Modifier.clickable {
                navController.navigate(Screen.Register.route) },
               text =  "Don´t have an account? Sign up now!")
            Spacer(modifier = Modifier.height(16.dp))
//            Button(
//                onClick = {
//                    navController.navigate(Screen.Register.route)
//                },
//                content = {
//                    Text("Sign up")
//                }
//            )

            if (state.error != null)
                Text(state.error?:"")
            if (state.isLoading)
                CircularProgressIndicator()


        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    ShoppingListTheme {
        LoginViewContent(state=LoginState())

    }
}

