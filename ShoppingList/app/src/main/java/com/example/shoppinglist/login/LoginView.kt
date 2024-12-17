package com.example.shoppinglist.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.Screen
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
        ){  Text(modifier=modifier
            .padding(16.dp),
            fontSize=30.sp,
            text="WELCOME BACK!")
//            Spacer(modifier = Modifier.height(30.dp))
//            Text(modifier=modifier
//                .padding(16.dp),
//                fontSize=20.sp,
//                text="Login to your account")
            Spacer(modifier = Modifier.height(30.dp))
            TextField(
                modifier=Modifier,
                    //.fillMaxWidth(),
                value = state.email,
                onValueChange = {
                   onEmailChange(it)
                },
                placeholder = {
                    Text("email")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                modifier=Modifier,
                //.fillMaxWidth(),
                value = state.password,
                onValueChange = {
                   onPasswordChange(it)
                },
                placeholder = {
                    Text("password")
                },
                visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier=Modifier
                    .padding(16.dp),
                    //.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6650a4),
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    ),
                onClick = {
                   onLoginClick()
                },
                content = {
                    Text("Login")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(modifier = Modifier
                .clickable {
                navController.navigate(Screen.Register.route) },
                fontSize = 20.sp,
               text =  "Don´t have an account? Sign up now!")
            Spacer(modifier = Modifier.height(16.dp))

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
        LoginViewContent(state= LoginState())

    }
}

