package com.example.socially

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backButton = findViewById<android.widget.ImageButton>(R.id.back_button)
        val userName = findViewById<EditText>(R.id.userNameInput)
        val pass = findViewById<EditText>(R.id.passInput)
        val loginButton = findViewById<TextView>(R.id.loginButton)
        val signUpButton = findViewById<TextView>(R.id.signupButton)

        val receivedUsername = intent.getStringExtra("username") ?: ""
        val receivedPassword = intent.getStringExtra("password") ?: ""
        val dpUriString = intent.getStringExtra("dpUri")

        userName.setText(receivedUsername)

        backButton.setOnClickListener {
            val intent = Intent(this, AskLoginPage::class.java)
            startActivity(intent)
            finish()
        }

        loginButton.setOnClickListener {
            val enteredUsername = userName.text.toString()
            val enteredPassword = pass.text.toString()

            if (enteredUsername == receivedUsername && enteredPassword == receivedPassword) {
                // Credentials match, go to HomePage
                val intent = Intent(this, HomePage::class.java)
                intent.putExtra("dpUri", dpUriString)
                intent.putExtra("username", receivedUsername)
                startActivity(intent)
                finish()
            } else {
                pass.error = "Incorrect username or password"
            }
        }

        signUpButton.setOnClickListener {
            val intent = Intent(this, SignupPage::class.java)
            startActivity(intent)
            finish()
        }
    }
}