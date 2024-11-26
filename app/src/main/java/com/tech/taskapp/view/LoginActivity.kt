package com.tech.taskapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tech.taskapp.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val loginButton: Button = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (isValidEmail(email) && isValidPassword(password)) {
                navigateToHome()
                // Email and password are valid, proceed with login
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function to validate email format
    private fun isValidEmail(email: String): Boolean {
        return if (email.isEmpty()) {
            showToast("Email cannot be empty")
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Invalid email format")
            false
        } else {
            true
        }
    }

    // Function to validate password length (minimum 6 characters)
    private fun isValidPassword(password: String): Boolean {
        return if (password.length < 6) {
            showToast("Password must be at least 6 characters long")
            false
        } else {
            true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun navigateToHome() {
        // Navigate to HomeActivity after successful login
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish() // Finish LoginActivity so it doesn't appear in the back stack
    }
}
