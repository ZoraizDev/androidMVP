package com.tech.taskapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.tech.taskapp.view.LoginActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Initialize the splash screen
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        // Keep the splash screen visible for a short duration
        splashScreen.setKeepOnScreenCondition { true }

        // Simulating a delay for splash (optional)
        Thread {
            Thread.sleep(2000) // 2 seconds delay
            runOnUiThread {
                // After delay, navigate to LoginActivity
                navigateToLogin()
            }
        }.start()
    }

    // Function to navigate to the Login screen
    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Call finish() to close the splash screen activity
    }
}
