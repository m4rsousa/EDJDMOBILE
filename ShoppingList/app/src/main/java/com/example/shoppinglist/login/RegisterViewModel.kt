package com.example.shoppinglist.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


data class RegisterState(
    val user: String ="",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val error: String? = null,
    val isLoading: Boolean = false
)

class RegisterViewModel : ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state


    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _state.value = _state.value.copy(confirmPassword = confirmPassword)
    }

    fun onRegisterClick(onRegisterSuccess: () -> Unit) {
        val email = _state.value.email
        val password = _state.value.password
        val confirmPassword = _state.value.confirmPassword

        // Basic validation
        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            _state.value = _state.value.copy(error = "All fields are required")
            return
        }
        if (password != confirmPassword) {
            _state.value = _state.value.copy(error = "Passwords do not match")
            return
        }

        _state.value = _state.value.copy(isLoading = true, error = null)

        // Firebase Registration
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _state.value = _state.value.copy(isLoading = false)
                        onRegisterSuccess() // Call success callback
                    } else {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = task.exception?.localizedMessage ?: "Registration failed"
                        )
                    }
                }
        }
    }
}
