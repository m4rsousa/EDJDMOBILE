package com.example.shoppinglist.login

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.ui.theme.ShoppingListTheme

@Composable
fun RegisterView(
    modifier: Modifier = Modifier,
    onRegisterSuccess: () -> Unit = {},
    navController: NavController = rememberNavController()
) {
    val viewModel: RegisterViewModel = viewModel()
    val state = viewModel.state.collectAsState().value

    RegisterViewContent(
        modifier = modifier,
        state = state,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
        onRegisterClick = {
            viewModel.onRegisterClick(onRegisterSuccess)
        }
    )

}
    @Composable
    fun RegisterViewContent(
        modifier: Modifier = Modifier,
        state: RegisterState,
        onEmailChange : (String)->Unit ={},
        onPasswordChange : (String)->Unit ={},
        onConfirmPasswordChange : (String)->Unit ={},
        onRegisterClick: () -> Unit = {}
    ) {

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Email TextField
            TextField(
                value = state.email,
                onValueChange = { onEmailChange(it) },
                placeholder = { Text("Email") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Password TextField
            TextField(
                value = state.password,
                onValueChange = { onPasswordChange(it) },
                placeholder = { Text("Password") },
                visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Confirm Password TextField
            TextField(
                value = state.confirmPassword,
                onValueChange = { onConfirmPasswordChange(it) },
                placeholder = { Text("Confirm Password") },
                visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Register Button
            Button(
                modifier= Modifier
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6650a4),
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                ),
                onClick = {
                    onRegisterClick()

                },
                content = { Text("Register") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Display error or loading indicators
            if (state.error != null) {
                Text(state.error ?: "", color = Color.Red)
            }
            if (state.isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterViewPreview() {
    ShoppingListTheme {
       RegisterViewContent(
           state = RegisterState()
       )
    }
}