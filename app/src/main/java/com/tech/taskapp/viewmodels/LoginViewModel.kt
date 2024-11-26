package com.tech.taskapp.viewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns

class LoginViewModel : ViewModel() {

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> get() = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> get() = _passwordError

    private val _isLoginSuccessful = MutableLiveData<Boolean>()
    val isLoginSuccessful: LiveData<Boolean> get() = _isLoginSuccessful

    fun validateCredentials(email: String, password: String) {
        var isValid = true

        // Email validation
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailError.value = "Invalid email format"
            isValid = false
        } else {
            _emailError.value = null
        }

        // Password validation
        if (password.length < 6) {
            _passwordError.value = "Password must be at least 6 characters"
            isValid = false
        } else {
            _passwordError.value = null
        }

        // If both email and password are valid
        if (isValid) {
            // You can implement authentication logic here if needed.
            _isLoginSuccessful.value = true
        } else {
            _isLoginSuccessful.value = false
        }
    }
}
